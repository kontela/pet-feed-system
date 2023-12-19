package com.example.PetFeeder.service;

import com.example.PetFeeder.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PetFeeder.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationRetrieveService {

    private NotificationRepository notificationRepository;


    @Autowired
    public NotificationRetrieveService(NotificationRepository notificationRepository){
        this.notificationRepository=notificationRepository;
    }

    public List<Notification>  retrieveAllNotification(){
    List<Notification> notifications=notificationRepository.findAll();
    notificationRepository.deleteAll();

    return notifications;
    }
}
