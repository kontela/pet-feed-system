package com.example.PetFeeder.service;

import com.example.PetFeeder.model.WaterLog;
import com.example.PetFeeder.repository.WaterLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WaterUpdateService {


    WaterLogRepository waterLogRepository;
    public WaterUpdateService(WaterLogRepository waterLogRepository){
        this.waterLogRepository=waterLogRepository;
    }
    public void updateWater(int number) {
        WaterLog waterLog =new WaterLog();
        waterLog.setPercentage(number);
        waterLog.setTimestamp(LocalDateTime.now());

        waterLogRepository.save(waterLog);
    }
}
