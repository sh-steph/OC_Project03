package com.openclassrooms.occhatop.dao;

import com.openclassrooms.occhatop.models.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RentalsResponse {
        private Iterable<Rental> rentals;
    }
}
