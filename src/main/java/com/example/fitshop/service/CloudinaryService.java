package com.example.fitshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadPicture(MultipartFile picture) throws IOException;

}
