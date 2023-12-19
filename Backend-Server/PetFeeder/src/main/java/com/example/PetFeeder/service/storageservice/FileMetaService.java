package com.example.PetFeeder.service.storageservice;

import com.example.PetFeeder.model.FileMeta;
import com.example.PetFeeder.repository.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<FileMeta> getAllFileMetas() {
        return fileMetaRepository.findAll();
    }

    public Optional<FileMeta> getFileMeta(Long id){
        return fileMetaRepository.findById(id);

    }

    public boolean deleteFileMeta(Long id){
        fileMetaRepository.deleteById(id);
        return true;
    }
}
