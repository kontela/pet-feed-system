package com.example.PetFeeder.service;

import com.example.PetFeeder.model.PetFoodLog;
import com.example.PetFeeder.repository.PetFoodLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PetFoodLogRetrieveLatestService {

    private PetFoodLogRepository petFoodLogRepository;

    @Autowired
    public PetFoodLogRetrieveLatestService(PetFoodLogRepository petFoodLogRepository) {
        this.petFoodLogRepository = petFoodLogRepository;
    }


    public PetFoodLog retrieveLatestPetFoodLog(){
        return petFoodLogRepository.findTopByOrderByTimestampDesc()
                .orElseThrow(() -> new NoSuchElementException("No pet food log found."));
    }
}
