package com.example.PetFeeder.service.storageservice.localfilesystem;

import com.example.PetFeeder.model.FileMeta;
import com.example.PetFeeder.repository.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileMetaService {

    private FileMetaRepository fileMetaRepository;

    @Autowired
    public FileMetaService(FileMetaRepository fileMetaRepository){
        this.fileMetaRepository=  fileMetaRepository;
    }

    public FileMeta createAndSaveFileMeta(LocalDateTime timeOfDay, String uploadDir) {
        FileMeta file = new FileMeta();
        file.setTimeOfDay(timeOfDay);
        file.setUploadDir(uploadDir);
        return fileMetaRepository.save(file);
    }

}
