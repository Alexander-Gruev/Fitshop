package com.example.fitshop.web;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.binding.ProductAddBindingModel;
import com.example.fitshop.model.service.ProductServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/details/{id}")
    public String productsDetails(@PathVariable Long id, Model model) {
        ProductDetailsViewModel productModel = this.productService.getById(id);
        model.addAttribute("productModel", productModel);
        return "product-details";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public String add() {
        return "product-add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String add(@Valid ProductAddBindingModel productAddBindingModel,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productAddBindingModel", productAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);

            return "redirect:/products/add";
        }

        this.productService
                .add(this.modelMapper.map(productAddBindingModel, ProductServiceModel.class));

        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("products", this.productService.getAll());
        return "products";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/weights")
    public String weights(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.WEIGHTS));
        return "products";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cardio")
    public String cardio(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.CARDIO));
        return "products";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/bands")
    public String bands(Model model) {
        model.addAttribute("products", this.productService.getByCategory(ProductCategoryEnum.BAND));
        return "products";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/new")
    public String newest(Model model) {
        model.addAttribute("products", this.productService.getTheNewestEight());
        return "products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products/all";
    }

}
