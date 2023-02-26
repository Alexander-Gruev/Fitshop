package com.fitshop.web;

import com.fitshop.enums.ProductCategoryEnum;
import com.fitshop.model.binding.ProductAddBindingModel;
import com.fitshop.model.binding.ProductUpdateBindingModel;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.service.ProductAddServiceModel;
import com.fitshop.model.service.ProductUpdateServiceModel;
import com.fitshop.model.view.ProductDetailsViewModel;
import com.fitshop.scheduler.CacheEvicter;
import com.fitshop.service.ProductService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CacheEvicter cacheEvicter;

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/details/{id}")
    public String productsDetails(@PathVariable Long id, Model model) {
        ProductDetailsViewModel productModel = this.productService.getViewModelById(id);
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
                .add(this.modelMapper.map(productAddBindingModel, ProductAddServiceModel.class));

        return "redirect:/products/all";
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.productService.deleteById(id);
        this.cacheEvicter.evictAllCacheValues();
        return "redirect:/products/all";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        ProductEntity productEntity = this.productService.getById(id);
        ProductUpdateBindingModel productUpdateBindingModel = this.modelMapper.map(productEntity, ProductUpdateBindingModel.class);

        model.addAttribute("productUpdateBindingModel", productUpdateBindingModel);
        return "product-update";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public String update(@Valid ProductUpdateBindingModel productUpdateBindingModel,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productUpdateBindingModel", productUpdateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productUpdateBindingModel", bindingResult);

            return "redirect:/products/update/" + id + "/error";
        }

        this.productService
                .update(this.modelMapper.map(productUpdateBindingModel, ProductUpdateServiceModel.class));
        this.cacheEvicter.evictAllCacheValues();

        return "redirect:/products/all";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}/error")
    public String updateError() {
        return "product-update";
    }

}
