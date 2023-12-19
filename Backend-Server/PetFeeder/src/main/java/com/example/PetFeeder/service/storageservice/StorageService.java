package com.example.PetFeeder.service.storageservice;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {   //extendable interface, depend on the profile (prod or dev) it can autoconfigure.
    void storeFile(MultipartFile file);

    boolean deleteFile(Long id);

    Resource retrieveFile(Long id);
}
