package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.WaterLog;
import com.example.PetFeeder.service.WaterRetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/log/retrieveLatestWater")
@RestController
public class WaterRetrieveController {


    private WaterRetrieveService waterRetrieveService;
    @Autowired
    public WaterRetrieveController(WaterRetrieveService waterRetrieveService){
        this.waterRetrieveService=waterRetrieveService;
    }

    @GetMapping
    public String retrieveLatestWaterLog(){
        return waterRetrieveService.retrieveLatestWaterLog();
    }



}
