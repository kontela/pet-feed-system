package com.example.PetFeeder.controller;


import com.example.PetFeeder.model.FeedingSchedule;
import com.example.PetFeeder.model.Notification;
import com.example.PetFeeder.service.NotificationRetrieveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Notification/retrieveAll")
public class NotificationRetrieveController {


    // A bit quick and dirt code to notify user that scheduled feeding time is not performed feeding action
    // Due to that there are still pet food on the weighter.

    private NotificationRetrieveService notificationRetrieveService;

    public NotificationRetrieveController(NotificationRetrieveService notificationRetrieveService){
        this.notificationRetrieveService=notificationRetrieveService;

    }

    @GetMapping
    public ResponseEntity<List<Notification>> retrieveAllNotification(){
        List<Notification> notifications =notificationRetrieveService.retrieveAllNotification();
        return ResponseEntity.ok(notifications);

    }



}
