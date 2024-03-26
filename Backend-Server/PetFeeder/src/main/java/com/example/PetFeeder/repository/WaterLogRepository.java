package com.example.PetFeeder.repository;

import com.example.PetFeeder.model.PetFoodLog;
import com.example.PetFeeder.model.WaterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaterLogRepository extends JpaRepository<WaterLog, Long> {

    Optional<WaterLog> findTopByOrderByTimestampDesc();

}
