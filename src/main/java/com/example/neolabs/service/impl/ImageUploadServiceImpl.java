package com.example.neolabs.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.neolabs.exception.EmptyFileException;
import com.example.neolabs.service.ImageUploadService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @SneakyThrows
    public String saveImage(MultipartFile multipartfile) {
        if (multipartfile.isEmpty()) {
            throw new EmptyFileException("File is empty");
        }

        final String urlKey = "cloudinary://532431178934438:dziz4lD4M6_iip6t1tuF0an_N8Q@db5aw8xbo";

        Cloudinary cloudinary = new Cloudinary((urlKey));

        File saveFile = Files.createTempFile(
                        System.currentTimeMillis() + "",
                        Objects.requireNonNull
                                (multipartfile.getOriginalFilename(), "File must have an extension")
                )
                .toFile();

        multipartfile.transferTo(saveFile);

        Map upload = cloudinary.uploader().upload(saveFile, ObjectUtils.emptyMap());

        return (String) upload.get("url");
    }
}
