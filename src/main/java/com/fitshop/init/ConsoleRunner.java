package com.fitshop.init;

import com.fitshop.service.ProductService;
import com.fitshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;

    @Override
    public void run(String... args) {
        this.productService.initProducts();
        this.userService.initUsersAndRoles();
    }
}
