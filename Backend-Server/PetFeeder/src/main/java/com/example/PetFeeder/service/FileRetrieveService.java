package com.example.PetFeeder.service;

import com.example.PetFeeder.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileRetrieveService {

    StorageService storageService;

    @Autowired
    public FileRetrieveService(StorageService storageService){
        this.storageService=storageService;
    }


    public Resource retrieveFile(Long id){

        return storageService.retrieveFile(id);

    }


}
