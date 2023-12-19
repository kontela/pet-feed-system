package com.example.PetFeeder.service;

import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.repository.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedingScheduleRetrieveAllService {
    FeedingScheduleRepository feedingScheduleRepository;

    @Autowired
    public FeedingScheduleRetrieveAllService(FeedingScheduleRepository feedingScheduleRepository){
        this.feedingScheduleRepository=feedingScheduleRepository;
    }

    public List<FeedingSchedule> FeedingScheduleRetrieveAll(){
       return feedingScheduleRepository.findAll();
    }
}
