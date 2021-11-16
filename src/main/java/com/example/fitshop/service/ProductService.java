package com.example.fitshop.service;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.service.ProductServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.model.view.ProductViewModel;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void initProducts();

    List<ProductViewModel> getTheFourCheapest();

    ProductDetailsViewModel getById(Long id);

    void add(ProductServiceModel productServiceModel) throws IOException;

    List<ProductViewModel> getAll();

    List<ProductViewModel> getTheNewestEight();

    List<ProductViewModel> getByCategory(ProductCategoryEnum category);

    void deleteById(Long id);

    void deleteByName(String productName);

    ProductEntity getByName(String name);

}
