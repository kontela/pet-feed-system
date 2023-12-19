package com.example.PetFeeder.service;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.model.PetFoodLog;
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
    private PetFoodLogRetrieveLatestService petFoodLogRetrieveLatestService;
    private FeedingScheduleRepository feedingScheduleRepository;
    private NotificationAddService notificationAddService;

    @Autowired
    FeedingScheduleTriggerService(FeedService feedService, FeedingScheduleRepository feedingScheduleRepository,PetFoodLogRetrieveLatestService petFoodLogRetrieveLatestService,NotificationAddService notificationAddService){
        this.feedService = feedService;
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.petFoodLogRetrieveLatestService=petFoodLogRetrieveLatestService;
        this.notificationAddService=notificationAddService;
    }

    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void checkAndTriggerEvents() {
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES); // Current time with hour and minute
        Optional<FeedingSchedule> optionalSchedule = feedingScheduleRepository.findByTimeOfDay(now);

        optionalSchedule.ifPresent(schedule -> {
            System.out.println("scheduled time is arrived!");

            if (schedule.getAutoFeed()) {
                PetFoodLog latestPetFood = petFoodLogRetrieveLatestService.retrieveLatestPetFoodLog();

                if (latestPetFood.getWeight() < 100) {
                    feedService.feed();
                    System.out.println("Feed command is given!");

                } else {
                    System.out.println("It has food inside already!");
                    String overWeightGram=String.valueOf(latestPetFood.getWeight());
                    String reason= "Autostop canceled scheduled feeding, food on place was :"+overWeightGram;
                    notificationAddService.addNotification(schedule.getTimeOfDay(),reason);

                }
            }
            else {
            feedService.feed();
            System.out.println("Feed command is given!");

            }}
        );
    };


}
