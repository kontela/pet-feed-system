package com.example.PetFeeder.service;

import com.example.PetFeeder.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDeleteService {

    StorageService storageService;
    @Autowired
    public FileDeleteService(StorageService storageService){
        this.storageService=storageService;
    }

    public boolean deleteFile(Long id){
        return storageService.deleteFile(id);
    }

}
