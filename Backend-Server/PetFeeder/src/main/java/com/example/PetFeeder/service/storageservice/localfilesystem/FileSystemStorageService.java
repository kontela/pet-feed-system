package com.example.PetFeeder.service.storageservice.localfilesystem;

import com.example.PetFeeder.model.FileMeta;
import com.example.PetFeeder.service.storageservice.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class FileSystemStorageService implements StorageService {

    //It is value annotation usage for easier configuration and seperating concerns.
    @Value("${file.upload-dir}")
    private String uploadDir;
    private FileMetaService fileMetaService;

    public FileSystemStorageService(FileMetaService fileMetaService){
        this.fileMetaService=fileMetaService;
    }

    @Override
    public void storeFile(MultipartFile file) {
        try {

            LocalDateTime currentTime = LocalDateTime.now();
            FileMeta savedFile = fileMetaService.createAndSaveFileMeta(currentTime, uploadDir);
            Path targetLocation = Paths.get(uploadDir).resolve(String.valueOf(savedFile.getId()));
            // Copying the file into the target local location
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error storing file", e);
        }
    }

    @Override
    public Resource retrieveFile(Long id) {

            try {
                // Construct the file path using the provided ID
                Path filePath = Paths.get(uploadDir).resolve(String.valueOf(id));

                // Load the file as a Resource
                Resource resource = new UrlResource(filePath.toUri());

                return resource;

            } catch (MalformedURLException ex) {
                throw new RuntimeException("Error loading file " + id, ex);
            }

    }


}


