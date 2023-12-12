package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FileRetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileRetrieveController {
    private FileRetrieveService fileRetrieveService;
    @Autowired
    public FileRetrieveController(FileRetrieveService fileRetrieveService){
        this.fileRetrieveService=fileRetrieveService;
    }

    @GetMapping("/retrieve/{id}")
    public Resource retrieveFile(@PathVariable Long id) {
        return fileRetrieveService.retrieveFile(id);
    }
}

