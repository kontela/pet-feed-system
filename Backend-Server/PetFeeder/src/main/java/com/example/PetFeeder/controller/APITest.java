package com.example.PetFeeder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITest {


    @GetMapping("/try")
    public ResponseEntity<String> feed(){

            return ResponseEntity.ok("Succesful load !: ");
        }
    }


