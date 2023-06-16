package com.openclassrooms.occhatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private Long id;
    private String email;
    private String name;
    private LocalDate created_at;
    private LocalDate updated_at;
}
