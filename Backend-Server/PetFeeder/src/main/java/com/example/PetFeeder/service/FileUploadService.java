package com.example.PetFeeder.service;

import com.example.PetFeeder.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    StorageService storageService;

    @Autowired
    public FileUploadService(StorageService storageService){
        this.storageService=storageService;
    }


    public void uploadFile(MultipartFile file){
        storageService.storeFile(file);
    }

}
