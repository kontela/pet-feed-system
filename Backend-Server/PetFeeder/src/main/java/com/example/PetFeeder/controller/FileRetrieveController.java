package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FileRetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@RequestMapping("/api/files/retrieve/{id}")
public class FileRetrieveController {
    private FileRetrieveService fileRetrieveService;
    @Autowired
    public FileRetrieveController(FileRetrieveService fileRetrieveService){
        this.fileRetrieveService=fileRetrieveService;
    }

/*
    @GetMapping
    public ResponseEntity<Resource> retrieveFile(@PathVariable Long id) {
        Resource fileResource = fileRetrieveService.retrieveFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileResource);
    }
*/

    @GetMapping
    public ResponseEntity<String> retrieveFileAsBase64(@PathVariable Long id) {
        Resource fileResource = fileRetrieveService.retrieveFile(id);

        // Read the file into a byte array
        byte[] fileArray;
        try (InputStream inputStream = fileResource.getInputStream()) {
            fileArray = inputStream.readAllBytes();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error reading file");
        }

        // Encode the byte array to Base64
        String base64Encoded = Base64.getEncoder().encodeToString(fileArray);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN) // or you can use another appropriate content type
                .body(base64Encoded);
    }
}

