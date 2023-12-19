package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.PetFoodLog;
import com.example.PetFeeder.service.PetFoodLogRetrieveLatestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log/retrieveLatest")
public class PetFoodLogRetrieveLatestController {


    // Endpoint for showing latest weight data to display on interface.

    private PetFoodLogRetrieveLatestService petFoodLogRetrieveLatestService;
    @Autowired
    public PetFoodLogRetrieveLatestController(PetFoodLogRetrieveLatestService petFoodLogRetrieveLatestService){
        this.petFoodLogRetrieveLatestService=petFoodLogRetrieveLatestService;
    }

    @GetMapping
    public PetFoodLog retrieveLatestPetFoodLog(){
        return petFoodLogRetrieveLatestService.retrieveLatestPetFoodLog();
    }
}
