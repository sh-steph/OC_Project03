package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.dto.RentalDTO;
import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Value("${images.upload.directory}")
    private String imageUploadDirectory;

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public Iterable<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getOneRental(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping()
    public ResponseEntity<?> save(@ModelAttribute("rentals") RentalDTO rentalDTO) {
        MultipartFile picture = rentalDTO.getPicture();
        if (picture != null && !picture.isEmpty()) {
            String fileName = picture.getOriginalFilename();
            String filePath = imageUploadDirectory + fileName;
            saveImageToFile(picture, filePath);
            rentalDTO.setPictureUrl("http://localhost:8090/images/rentals/" + fileName);
            rentalDTO.setPicture(picture);
        }

        rentalService.addNewRental(rentalDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        return uuid + "." + fileExtension;
    }

    private void saveImageToFile(MultipartFile picture, String filePath) {
        try {
            Path destinationPath = Path.of(filePath);
            Files.copy(picture.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
        return rentalService.updateRental(updateRental, id);
    }
}
