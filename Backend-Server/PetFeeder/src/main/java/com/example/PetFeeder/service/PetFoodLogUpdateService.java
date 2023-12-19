package com.example.PetFeeder.service;

import com.example.PetFeeder.model.PetFoodWeightDTO;
import com.example.PetFeeder.model.PetFoodLog;
import com.example.PetFeeder.repository.PetFoodLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PetFoodLogUpdateService {


    private PetFoodLogRepository petFoodLogRepository;
    private RestTemplate restTemplate;

    @Value("${device.arduino.url}")
    private String arduino_url;
    private String arduino_feed_route="/load";

    @Autowired
    public PetFoodLogUpdateService(PetFoodLogRepository petFoodLogRepository,RestTemplate restTemplate) {
        this.petFoodLogRepository = petFoodLogRepository;
        this.restTemplate=restTemplate;
    }


    public PetFoodLog logFoodWeight(PetFoodLog petFoodLog) {
        //if time is not stamped, then lets stamp it here.
        if (petFoodLog.getTimestamp() == null) {
            petFoodLog.setTimestamp(LocalDateTime.now());
        }

        return petFoodLogRepository.save(petFoodLog);
    }

    @Scheduled(cron = "*/10 * * * * *")         // Runs every minute
    public void autoRequestFoodWeightandLog(){  // Triggers Arduino to send weight data to backends server.
        try {
            String arduino_endpoint = arduino_url + arduino_feed_route;
            String response = restTemplate.getForObject(arduino_endpoint, String.class);
            System.out.println("Received response from Arduino: " + response);

        } catch (RestClientException e) {
            System.err.println("Error sending request to Arduino: " + e.getMessage());
        }
}
}
