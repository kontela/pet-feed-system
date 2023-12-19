package com.example.PetFeeder.repository;

import com.example.PetFeeder.model.FileMeta;
import com.example.PetFeeder.model.PetFoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetFoodLogRepository extends JpaRepository<PetFoodLog, Long> {

    Optional<PetFoodLog> findTopByOrderByTimestampDesc();
}
