package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.dto.RentalDTO;
import com.openclassrooms.occhatop.exceptions.RentalNotFoundException;
import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.repositories.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class RentalService {

    private RentalRepository rentalRepository;

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
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(null);
        rental.setPicture(rentalDTO.getPictureUrl());
        rentalRepository.save(rental);
    }

    public Rental updateRental(Rental rentalUpdate, Long id) {
        return rentalRepository.findById(id).map(rental -> {
            rental.setName(rentalUpdate.getName());
            rental.setDescription(rentalUpdate.getDescription());
            rental.setPicture(rentalUpdate.getPicture());
            rental.setSurface(rentalUpdate.getSurface());
            rental.setUpdatedAt();
            return rentalRepository.save(rental);
        }).orElseGet(() -> {
            rentalUpdate.setId(id);
            return rentalRepository.save(rentalUpdate);
        });
    }
}
