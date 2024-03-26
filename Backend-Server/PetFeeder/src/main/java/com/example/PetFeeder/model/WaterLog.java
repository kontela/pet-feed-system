package com.example.PetFeeder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "water_log")
public class WaterLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "water_log_id")
    private Long id;

    @Column(name = "timeOfDay")
    private LocalDateTime timestamp;

    @Column(name = "percentage")
    private Integer percentage;

}
