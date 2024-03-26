package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.WaterUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/log/updateWater")
@RestController
public class WaterUpdateController {

    private WaterUpdateService waterUpdateService;
    @Autowired
    public WaterUpdateController(WaterUpdateService waterUpdateService){
        this.waterUpdateService=waterUpdateService;
    }
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam(name = "number") int number) {
        waterUpdateService.updateWater(number);
        return ResponseEntity.ok("Water updated successfully: " );
    }

}
