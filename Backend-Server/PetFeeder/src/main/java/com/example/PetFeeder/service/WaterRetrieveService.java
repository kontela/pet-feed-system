package com.example.PetFeeder.service;


import com.example.PetFeeder.model.WaterLog;
import com.example.PetFeeder.repository.WaterLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WaterRetrieveService {


    WaterLogRepository waterLogRepository;
    @Autowired
    public WaterRetrieveService(WaterLogRepository waterLogRepository){
        this.waterLogRepository = waterLogRepository;

    }
    public String retrieveLatestWaterLog() {


        Optional<WaterLog> result = waterLogRepository.findTopByOrderByTimestampDesc();
        return result.get().getPercentage().toString();

        }
    }

