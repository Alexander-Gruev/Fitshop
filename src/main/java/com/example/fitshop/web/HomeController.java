package com.example.fitshop.web;

import com.example.fitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cheapestProducts", this.productService.getTheFourCheapest());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/beginners")
    public String beginners() {
        return "beginner";
    }

    @GetMapping("/intermediates")
    public String intermediates() {
        return "intermediate";
    }

    @GetMapping("/advanced")
    public String advanced() {
        return "advanced";
    }
}
