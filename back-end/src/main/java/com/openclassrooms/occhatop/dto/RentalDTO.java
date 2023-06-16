package com.openclassrooms.occhatop.dto;

import com.openclassrooms.occhatop.models.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalDTO {
    private Long id;
    private String name;
    private double surface;
    private double price;
    private MultipartFile picture;
    private String pictureUrl;
    private String description;
    private Long ownerId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
