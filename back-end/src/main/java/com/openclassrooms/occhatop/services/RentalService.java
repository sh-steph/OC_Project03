package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.dto.RentalDTO;
import com.openclassrooms.occhatop.exceptions.RentalNotFoundException;
import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.repositories.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

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
        rental.setOwner_id(rentalDTO.getOwner_id());
        rental.setCreated_at(LocalDateTime.now());
        rental.setUpdated_at(null);
        rental.setPicture(rentalDTO.getPictureUrl());
        rentalRepository.save(rental);
    }

    public Rental updateRental(RentalDTO rentalUpdate, Long id) {
        Optional<Rental> search = rentalRepository.findById(id);
        if(!search.isEmpty()) {
            Rental rental = search.get();
            rental.setName(rentalUpdate.getName());
            rental.setSurface(rentalUpdate.getSurface());
            rental.setPrice(rentalUpdate.getPrice());
            rental.setDescription(rentalUpdate.getDescription());
            rental.setOwner_id(rentalUpdate.getOwner_id());
            rental.setPicture(rentalUpdate.getPictureUrl());
            rental.setUpdated_at(LocalDateTime.now());
            return rentalRepository.save(rental);
        } else {
            return null;
        }
    }
}
