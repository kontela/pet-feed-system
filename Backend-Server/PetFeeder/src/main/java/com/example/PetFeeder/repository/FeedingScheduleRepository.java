package com.example.PetFeeder.repository;

import com.example.PetFeeder.model.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {

    Optional<FeedingSchedule> findByTimeOfDay(LocalTime scheduledTime);


}

