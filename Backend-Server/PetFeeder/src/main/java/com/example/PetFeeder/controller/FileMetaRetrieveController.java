package com.example.PetFeeder.controller;

import com.example.PetFeeder.model.FileMeta;
import com.example.PetFeeder.service.storageservice.FileMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/files/retrieve/meta")
public class FileMetaRetrieveController {

    // This endpoints sends all object detected images (cats) to interface (mobile app)
    // Hence interface pin point which photo it wants, such as latest object detected photo etc.
    FileMetaService fileMetaService;

    @Autowired
    public FileMetaRetrieveController(FileMetaService fileMetaService){
        this.fileMetaService=fileMetaService;
    }

    @GetMapping
    public ResponseEntity<List<FileMeta>> getAllFileMetas(){
        List<FileMeta> fileMetas = fileMetaService.getAllFileMetas();
        return ResponseEntity.ok(fileMetas);
    }
}
