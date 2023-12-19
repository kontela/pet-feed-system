package com.example.PetFeeder.repository;

import com.example.PetFeeder.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
