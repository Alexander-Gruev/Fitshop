package com.example.fitshop.init;

import com.example.fitshop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ProductService productService;

    public ConsoleRunner(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.productService.initProducts();
    }
}
