package com.example.PetFeeder.service;

import com.example.PetFeeder.model.Notification;
import com.example.PetFeeder.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class NotificationAddService {

    NotificationRepository notificationRepository;


    @Autowired
    public NotificationAddService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public void addNotification(LocalTime timeOfOccurence, String reason){
        Notification notification = new Notification(reason, timeOfOccurence);
        notificationRepository.save(notification);


    }
}
