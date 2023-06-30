package com.openclassrooms.occhatop.models.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "MESSAGES")
public class Message {
    @Id
    @Schema(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_id", nullable = false)
    private Long rental_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    private String message;

    @Schema(hidden = true)
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Schema(hidden = true)
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Schema(hidden = true)
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }
}
