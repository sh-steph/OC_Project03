package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.services.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

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

    @PostMapping
    public void create(@RequestBody Rental rental) {
        rentalService.addNewRental(rental);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
        return rentalService.updateRental(updateRental, id);
    }
}
