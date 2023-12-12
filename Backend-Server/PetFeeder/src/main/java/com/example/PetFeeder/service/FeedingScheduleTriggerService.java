package com.example.PetFeeder.service;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.repository.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class FeedingScheduleTriggerService {

    private final FeedService feedService;
    private FeedingScheduleRepository repository;

    @Autowired
    FeedingScheduleTriggerService(FeedService feedService, FeedingScheduleRepository repository){
        this.feedService = feedService;
        this.repository=repository;
    }

    @Scheduled(cron = "0 * * * * *") // Run every minute
    public void checkAndTriggerEvents() {
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES); // Current time with hour and minute
        Optional<FeedingSchedule> optionalSchedule = repository.findByTimeOfDay(now);

        optionalSchedule.ifPresent(schedule -> {
            System.out.println("scheduled time is arrived!");
            feedService.feed(); // Trigger the feed action if a schedule is present
        });
    }


}
