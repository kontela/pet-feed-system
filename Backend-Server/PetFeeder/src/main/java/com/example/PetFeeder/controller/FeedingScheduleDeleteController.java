package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FeedingScheduleDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeding-schedule/delete/{id}")
public class FeedingScheduleDeleteController {

    // Endpoint for the user delete his/her setted schedules
    FeedingScheduleDeleteService feedingScheduleDeleteService;

    @Autowired

    public FeedingScheduleDeleteController(FeedingScheduleDeleteService feedingScheduleDeleteService){

        this.feedingScheduleDeleteService=feedingScheduleDeleteService;
    }

    @GetMapping
    public ResponseEntity<?> deleteFeedingSchedule(@PathVariable Long id){
        feedingScheduleDeleteService.feedingScheduleDelete(id);
        return ResponseEntity.ok("Successfully deleted");

    }
}
