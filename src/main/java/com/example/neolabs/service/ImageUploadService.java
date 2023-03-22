package com.example.neolabs.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    String saveImage(MultipartFile multipartFile);
}
