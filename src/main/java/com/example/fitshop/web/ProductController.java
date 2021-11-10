package com.example.fitshop.web;

import com.example.fitshop.model.binding.ProductAddBindingModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("/details/{id}")
    public String productsDetails(@PathVariable Long id, Model model) {
        ProductDetailsViewModel productModel = this.productService.getById(id);
        model.addAttribute("productModel", productModel);
        return "product-details";
    }

    @GetMapping("/add")
    public String add() {
        return "product-add";
    }



}
