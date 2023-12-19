package com.example.PetFeeder.service;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.repository.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedingScheduleDeleteService {

    FeedingScheduleRepository feedingScheduleRepository;

    @Autowired
    FeedingScheduleDeleteService(FeedService feedService, FeedingScheduleRepository feedingScheduleRepository){
        this.feedingScheduleRepository = feedingScheduleRepository;
    }


    public void feedingScheduleDelete(Long id){

        feedingScheduleRepository.deleteById(id);

    }

}
