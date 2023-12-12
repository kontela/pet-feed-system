package com.example.PetFeeder.controller;

import com.example.PetFeeder.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private FileUploadService fileUploadService;
    @Autowired
    public FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService=fileUploadService;
    }
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(file);
        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    }
}
