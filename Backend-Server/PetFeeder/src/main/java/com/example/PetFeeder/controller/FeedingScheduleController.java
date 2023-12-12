package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.service.FeedingScheduleSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeding-schedule")
public class FeedingScheduleController {
    @Autowired
    private FeedingScheduleSetService feedingScheduleSetService;

    @PostMapping
    public FeedingSchedule setFeedingSchedule(@RequestBody FeedingSchedule schedule) {
        return feedingScheduleSetService.saveSchedule(schedule);
}
    //setFeedingSchedule with given arguments -> start time, frequency, autostop, amount.
    // FeedingScheduleSetService will have one more new method.
}
