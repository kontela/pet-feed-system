package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.service.FeedingScheduleRetrieveAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feeding-schedule/retrieveAll")
public class FeedingScheduleRetrieveAllController {

    // Endpoint for user to display all its scheduled feedings.

    FeedingScheduleRetrieveAllService feedingScheduleRetrieveAllService;

    @Autowired
    public FeedingScheduleRetrieveAllController(FeedingScheduleRetrieveAllService feedingScheduleRetrieveAllService){
        this.feedingScheduleRetrieveAllService=feedingScheduleRetrieveAllService;
    }

    @GetMapping
    public ResponseEntity<List<FeedingSchedule>> retrieveAllFeedingSchedule(){
        List<FeedingSchedule> feedingSchedules =feedingScheduleRetrieveAllService.FeedingScheduleRetrieveAll();
        return ResponseEntity.ok(feedingSchedules);

    }
}
