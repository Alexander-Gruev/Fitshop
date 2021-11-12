package com.example.fitshop.web;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.binding.ProductAddBindingModel;
import com.example.fitshop.model.service.ProductServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
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

    @PostMapping("/add")
    public String add(ProductAddBindingModel productAddBindingModel) throws IOException {
        this.productService
                .add(this.modelMapper.map(productAddBindingModel, ProductServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("products", this.productService.getAll());
        return "products";
    }

    @GetMapping("/weights")
    public String weights(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.WEIGHTS));
        return "products";
    }

    @GetMapping("/cardio")
    public String cardio(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.CARDIO));
        return "products";
    }

    @GetMapping("/bands")
    public String bands(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.BAND));
        return "products";
    }

    @GetMapping("/new")
    public String newest(Model model) {
        model.addAttribute("products", this.productService.getTheNewestEight());
        return "products";
    }

}
