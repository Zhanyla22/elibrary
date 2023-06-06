package com.example.neolabs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String saveFile(MultipartFile multipartFile);
}
