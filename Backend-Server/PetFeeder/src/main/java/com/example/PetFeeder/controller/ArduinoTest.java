package com.example.PetFeeder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArduinoTest {


    @GetMapping("/try")
    public void feed(){

        System.out.println("it worked, request has been recieved by arduino");
    }

}
