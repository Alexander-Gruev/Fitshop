package com.example.fitshop.web;

import com.example.fitshop.model.view.ProductViewModel;
import com.example.fitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ProductViewModel> theFourCheapest = this.productService.getTheFourCheapest();
        model.addAttribute("cheapestProducts", this.productService.getTheFourCheapest());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
