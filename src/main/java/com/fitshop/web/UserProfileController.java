package com.fitshop.web;

import com.fitshop.model.binding.UserPictureBindingModel;
import com.fitshop.model.service.UserPictureServiceModel;
import com.fitshop.model.view.UserViewModel;
import com.fitshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

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
        UserPictureServiceModel userPictureServiceModel = UserPictureServiceModel.builder()
                .picture(userPictureBindingModel.getPicture())
                .username(principal.getName())
                .build();

        this.userService.updateWithPicture(userPictureServiceModel);
        return "redirect:/users/profile";
    }

}
