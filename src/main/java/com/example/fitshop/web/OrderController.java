package com.example.fitshop.web;

import com.example.fitshop.model.binding.OrderBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @ModelAttribute
    public OrderBindingModel orderBindingModel() {
        return new OrderBindingModel();
    }

    @GetMapping("/new")
    public String newOrder() {
        return "order-new";
    }

    @PostMapping("/new")
    public String newOrder(@Valid OrderBindingModel orderBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("orderBindingModel", orderBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel", bindingResult);

            return "redirect:/orders/new";
        }

        // todo order service add
        return "order-new";
    }
}
