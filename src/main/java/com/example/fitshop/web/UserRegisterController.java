package com.example.fitshop.web;

import com.example.fitshop.enums.UserExperienceEnum;
import com.example.fitshop.model.binding.UserRegisterBindingModel;
import com.example.fitshop.model.service.UserRegisterServiceModel;
import com.example.fitshop.model.view.UserViewModel;
import com.example.fitshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("experienceLevels", UserExperienceEnum.values());
        return "auth-register";
    }

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
        System.out.println();
        model.addAttribute("userViewModel", viewModel);
        return "profile";
    }




}
