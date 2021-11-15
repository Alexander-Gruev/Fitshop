package com.example.fitshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FitshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitshopApplication.class, args);
    }

}
