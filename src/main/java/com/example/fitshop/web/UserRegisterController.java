package com.example.fitshop.web;

import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.model.binding.UserPictureBindingModel;
import com.example.fitshop.model.binding.UserRegisterBindingModel;
import com.example.fitshop.model.service.UserPictureServiceModel;
import com.example.fitshop.model.service.UserRegisterServiceModel;
import com.example.fitshop.model.view.UserViewModel;
import com.example.fitshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserPictureBindingModel userPictureBindingModel() {
        return new UserPictureBindingModel();
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("experienceLevels", UserExperienceEnum.values());
        return "auth-register";
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel serviceModel =
                this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        this.userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserViewModel viewModel = this.userService.getViewModelByUsername(principal.getName());
        model.addAttribute("userViewModel", viewModel);
        return "profile";
    }

    @PutMapping("/profile/{id}")
    public String profile(UserPictureBindingModel userPictureBindingModel, Principal principal, @PathVariable Long id) throws IOException {
        UserPictureServiceModel userPictureServiceModel = new UserPictureServiceModel();
        userPictureServiceModel
                .setPicture(userPictureBindingModel.getPicture())
                .setUsername(principal.getName());

        this.userService.updateWithPicture(userPictureServiceModel);
        return "redirect:/users/profile";
    }

}
