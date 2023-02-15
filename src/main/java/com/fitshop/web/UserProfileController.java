package com.fitshop.web;

import com.fitshop.model.binding.UserPictureBindingModel;
import com.fitshop.model.service.UserPictureServiceModel;
import com.fitshop.model.view.UserViewModel;
import com.fitshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserPictureBindingModel userPictureBindingModel() {
        return new UserPictureBindingModel();
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserViewModel viewModel = this.userService.getViewModelByUsername(principal.getName());
        model.addAttribute("userViewModel", viewModel);
        return "profile";
    }

    @PatchMapping("/profile/{id}")
    public String profile(UserPictureBindingModel userPictureBindingModel, Principal principal, @PathVariable Long id) throws IOException {
        UserPictureServiceModel userPictureServiceModel = new UserPictureServiceModel();
        userPictureServiceModel
                .setPicture(userPictureBindingModel.getPicture())
                .setUsername(principal.getName());

        this.userService.updateWithPicture(userPictureServiceModel);
        return "redirect:/users/profile";
    }

}
