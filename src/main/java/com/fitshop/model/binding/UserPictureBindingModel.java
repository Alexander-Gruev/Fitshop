package com.fitshop.model.binding;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserPictureBindingModel {

    private MultipartFile picture;

}
