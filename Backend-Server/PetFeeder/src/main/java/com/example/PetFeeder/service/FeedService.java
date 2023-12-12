package com.example.PetFeeder.service;

import com.example.PetFeeder.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FeedService {

    private final RestTemplate restTemplate;
    @Autowired
    public FeedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void feed() {

        //String url = "arduino url holder";
        //String response = restTemplate.getForObject(url,String.class);
        System.out.println("request has been sent to arduino device");
    }

}
