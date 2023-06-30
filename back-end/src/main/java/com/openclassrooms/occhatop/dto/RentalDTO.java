package com.openclassrooms.occhatop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(hidden = true)
    private Long id;
    private String name;
    private double surface;
    private double price;
    private MultipartFile picture;
    @Schema(hidden = true)
    private String pictureUrl;
    private String description;
    private Long ownerId;
    @Schema(hidden = true)
    private LocalDate createdAt;
    @Schema(hidden = true)
    private LocalDate updatedAt;
}
