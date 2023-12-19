package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.PetFoodLog;
import com.example.PetFeeder.service.PetFoodLogUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log/update")
public class PetFoodLogUpdateController {

    private PetFoodLogUpdateService petFoodLogUpdateService;

    @Autowired
    public PetFoodLogUpdateController(PetFoodLogUpdateService petFoodLogUpdateService) {
        this.petFoodLogUpdateService = petFoodLogUpdateService;
    }

    @PostMapping
    public ResponseEntity<PetFoodLog> logFoodWeight(@RequestBody PetFoodLog petFoodLog) {
        PetFoodLog savedLog = petFoodLogUpdateService.logFoodWeight(petFoodLog);
        return ResponseEntity.ok(savedLog);
    }
}
