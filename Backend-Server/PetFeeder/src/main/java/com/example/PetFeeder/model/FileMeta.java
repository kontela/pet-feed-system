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
@Table(name = "file_meta")
public class FileMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id")
    private Long id;

    @Column(name = "timeOfDay")
    private LocalDateTime timeOfDay;                        //timezone differences is not in scope.

    @Column(name="uploadDir")
    private String uploadDir;
}
