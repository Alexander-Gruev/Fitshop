package com.example.fitshop.web;

import com.example.fitshop.model.binding.OrderBindingModel;
import com.example.fitshop.model.service.OrderServiceModel;
import com.example.fitshop.service.OrderService;
import com.example.fitshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public OrderBindingModel orderBindingModel() {
        return new OrderBindingModel();
    }

    @GetMapping("/new/{name}")
    public String newOrder(@PathVariable String name, Model model) {
        model.addAttribute("productName", name);
        return "order-new";
    }

    @PostMapping("/new/{name}")
    public String newOrder(@Valid OrderBindingModel orderBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @PathVariable("name") String productName,
                           @AuthenticationPrincipal User user) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("orderBindingModel", orderBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel", bindingResult);

            return "redirect:/orders/new/{name}";
        }

        OrderServiceModel orderServiceModel = this.modelMapper.map(orderBindingModel, OrderServiceModel.class);
        orderServiceModel
                .setProductName(productName)
                .setClientUsername(user.getUsername());

        this.orderService.addOrder(orderServiceModel);

        return "redirect:/users/profile";
    }

    @GetMapping("/all")
    public String all() {
        return "orders-all";
    }
}
