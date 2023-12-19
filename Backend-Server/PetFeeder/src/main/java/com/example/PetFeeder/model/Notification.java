package com.example.PetFeeder.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_log_id")
    private Long id;

    private String reason;

    @Column(name = "timeOfOccurence")
    private LocalTime timeOfOccurence;

    public Notification(String reason, LocalTime timeOfOccurence) {
        this.reason=reason;
        this.timeOfOccurence=timeOfOccurence;

    }
}
