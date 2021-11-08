package com.example.fitshop.init;

import com.example.fitshop.service.ProductService;
import com.example.fitshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;

    public ConsoleRunner(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.productService.initProducts();
        this.userService.initUsersAndRoles();
    }
}
