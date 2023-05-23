package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.models.rental.Rental;
import com.openclassrooms.occhatop.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping()
    public Rental addNewRental(@RequestBody Rental newRental) {
        return rentalService.addNewRental(newRental);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@RequestBody Rental updateRental, @PathVariable Long id) {
        return rentalService.updateRental(updateRental, id);
    }
}
