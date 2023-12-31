package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feed;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feed = feedService;
    }

    @GetMapping
    public void feed(){
        feed.feed();

    }


}
