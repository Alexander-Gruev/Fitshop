package com.example.fitshop.service.impl;

import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.repository.ProductRepository;
import com.example.fitshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void initProducts() {
        if (this.productRepository.count() == 0) {
            ProductEntity bench = new ProductEntity();
            bench
                    .setBrandName("NordicTrack")
                    .setName("Adjustable bench")
                    .setPrice(BigDecimal.valueOf(500))
                    .setDescription("The NordicTrack Adjustable Weight Bench" +
                            "allows you to target a variety of muscles in your" +
                            "upper and lower body for a comprehensive and challenging" +
                            "workout in the privacy of your home. Easily adjust your weight" +
                            "bench between flat, incline, or military positions. Develop and tone" +
                            "your shoulders, upper chest, and triceps. Training in a decline will" +
                            "target your lower chest, lats, triceps, and front delts, allowing you to" +
                            "develop muscle evenly across your upper body. A high-density foam-padded backrest" +
                            "provides a comfortable lifting experience for sustained exercise. This helps you get" +
                            "the most out of your equipment. Your NordicTrack Adjustable Weight Bench features carefully" +
                            "box-stitched and sewn seats. These are more durable than stapled-on seat covers and add a" +
                            "professional touch to any home gym. Enjoy the added benefit of a professionally-designed" +
                            "exercise chart, developed by a certified personal trainer, to guide you through a variety of" +
                            "effective exercises and show proper form.")
                    .setImageUrl("/img/bench.jpeg");

        }
    }


}
