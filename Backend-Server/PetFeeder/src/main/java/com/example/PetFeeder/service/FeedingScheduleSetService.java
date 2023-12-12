package com.example.PetFeeder.service;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.repository.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class FeedingScheduleSetService {
    @Autowired
    private FeedingScheduleRepository repository;
    public FeedingSchedule saveSchedule(FeedingSchedule schedule) {
        // Save the schedule to the database
        LocalTime timeOfDayWithSeconds= schedule.getTimeOfDay();
        schedule.setTimeOfDay(timeOfDayWithSeconds.truncatedTo(ChronoUnit.MINUTES));

        return repository.save(schedule);
    }
}
