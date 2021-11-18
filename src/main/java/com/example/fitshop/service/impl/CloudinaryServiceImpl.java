package com.example.fitshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.fitshop.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String URL = "url";
    private static final String TEMP_FILE = "temp_file";
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadPicture(MultipartFile picture) throws IOException {
        File temp = File.createTempFile(TEMP_FILE, picture.getOriginalFilename());
        picture.transferTo(temp);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> result = this.cloudinary
                    .uploader()
                    .upload(temp, Map.of("folder", "Fitshop"));

            return result
                    .getOrDefault(
                            URL,
                            "https://res.cloudinary.com/algruev/image/upload/v1636620629/Fitshop/default_h96tkx.jpg");
        } finally {
            temp.delete();
        }
    }
}
