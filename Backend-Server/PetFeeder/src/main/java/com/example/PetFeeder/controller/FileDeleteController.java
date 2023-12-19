package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FileDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files/delete/{id}")
public class FileDeleteController {

    FileDeleteService fileDeleteService;

    @Autowired
    public FileDeleteController(FileDeleteService fileDeleteService){
        this.fileDeleteService=fileDeleteService;
    }

    //Simple endpoint mainly its goal usage for admin or user for delete specified files (cat detected images)
    @GetMapping
    public ResponseEntity<?> deleteFile(@PathVariable Long id){
        boolean isDeleted = fileDeleteService.deleteFile(id);
        if (isDeleted) {
            return ResponseEntity.ok("Successfully deleted");
        } else {
            // Not Found, assuming if the file or metadata does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Photo with ID: " + id + " not found or could not be deleted.");
        }
    }
}
