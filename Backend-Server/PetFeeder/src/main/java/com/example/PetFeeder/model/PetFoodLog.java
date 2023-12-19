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
@Table(name = "pet_food_log")
public class PetFoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_log_id")
    private Long id;

    @Column(name = "timeOfDay")
    private LocalDateTime timestamp; // The time the weight was recorded

    @Column(name = "weight")
    private Double weight; // Weight of the pet food

}
