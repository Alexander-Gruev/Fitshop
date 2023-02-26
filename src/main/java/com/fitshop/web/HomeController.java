package com.fitshop.web;

import com.fitshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

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
