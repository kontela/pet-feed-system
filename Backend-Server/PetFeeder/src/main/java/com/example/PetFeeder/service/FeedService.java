package com.example.PetFeeder.service;

import com.example.PetFeeder.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FeedService {


    @Value("${device.arduino.url}")
    private String arduino_url;
    private String arduino_feed_route="/servo";


    private final RestTemplate restTemplate;
    @Autowired
    public FeedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void feed() {

        try {
            String arduino_endpoint=arduino_url+arduino_feed_route;
            restTemplate.getForObject(arduino_endpoint, String.class);
            System.out.println("Request has been sent to Arduino device");
        } catch (RestClientException e) {
            System.err.println("Error sending request to Arduino: " + e.getMessage());
        }    }

}
