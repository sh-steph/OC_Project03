package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.dto.RentalDTO;
import com.openclassrooms.occhatop.exceptions.RentalNotFoundException;
import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.repositories.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class RentalService {

    private RentalRepository rentalRepository;
    @Value("${images.upload.directory}")
    private String imageUploadDirectory;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException(id));
    }

    public Iterable<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void addNewRental(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setOwnerId(rentalDTO.getOwnerId());
        rental.setCreatedAt(LocalDate.now());
        rental.setUpdatedAt(null);
        rental.setPicture(rentalDTO.getPictureUrl());
        rentalRepository.save(rental);
    }

    public Rental updateRental(RentalDTO rentalUpdate, Long id) {
        Optional<Rental> search = rentalRepository.findById(id);
        if(!search.isEmpty()) {
            Rental rental = search.get();
            String previousImagePath = imageUploadDirectory + rental.getPicture();
            if (previousImagePath != null) {
                File previousImageFile = new File(previousImagePath);
                previousImageFile.delete();
            }
            rental.setName(rentalUpdate.getName());
            rental.setSurface(rentalUpdate.getSurface());
            rental.setPrice(rentalUpdate.getPrice());
            rental.setDescription(rentalUpdate.getDescription());
            rental.setOwnerId(rentalUpdate.getOwnerId());
            rental.setPicture(rentalUpdate.getPictureUrl());
            rental.setUpdatedAt(LocalDate.now());
            return rentalRepository.save(rental);
        } else {
            return null;
        }
    }
}
