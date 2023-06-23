package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.dto.RentalDTO;
import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.repositories.RentalRepository;
import com.openclassrooms.occhatop.services.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Value("${images.upload.directory}")
    private String imageUploadDirectory;

    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Get all rentals", description = "Get all rentals")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public Iterable<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @Operation(summary = "Get rental", description = "Get rental by id")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public Rental getOneRental(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @Operation(summary = "Post rental", description = "Post a new rental data")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> save(@ModelAttribute("rentals") RentalDTO rentalDTO) {
        MultipartFile picture = rentalDTO.getPicture();
        if (picture != null && !picture.isEmpty()) {
            String fileName = picture.getOriginalFilename();
            String filePath = imageUploadDirectory + fileName;
            saveImageFile(picture, filePath);
            rentalDTO.setPictureUrl("http://localhost:8090/images/rentals/" + fileName);
            rentalDTO.setPicture(picture);
        }
        rentalService.addNewRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Put rental", description = " Update a rental")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value ="/{id}", consumes = {"multipart/form-data"})
    public Rental updateRentalData(@ModelAttribute("rentals") RentalDTO updateRental, @PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        String previousFileName = rental.getPicture().substring(rental.getPicture().lastIndexOf("/") + 1);
        MultipartFile picture = updateRental.getPicture();
        if (picture != null && !picture.isEmpty()) {
            String fileName = picture.getOriginalFilename();
            String filePath = imageUploadDirectory + fileName;
            if (rental.getPicture() != null) {
                String previousFilePath = imageUploadDirectory + previousFileName;
                deleteImageFile(previousFilePath);
            }
            saveImageFile(picture, filePath);
            updateRental.setPictureUrl("http://localhost:3000/images/rentals/" + fileName);
            updateRental.setPicture(picture);
        }
        return rentalService.updateRental(updateRental, id);
    }

    private void saveImageFile(MultipartFile picture, String filePath) {
        try {
            File file = new File(filePath);
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(picture.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteImageFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                log.info("The following file has been deleted : {}", filePath);
            } else {
                log.warn("Impossible to delete the file : {}", filePath);
            }
        } else {
            log.warn("File not found : {}", filePath);
        }
    }
}
