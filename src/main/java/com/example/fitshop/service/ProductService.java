package com.example.fitshop.service;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.entity.ProductEntity;
import com.example.fitshop.model.service.ProductAddServiceModel;
import com.example.fitshop.model.service.ProductUpdateServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.model.view.ProductViewModel;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void initProducts();

    List<ProductViewModel> getTheFourCheapest();

    ProductDetailsViewModel getViewModelById(Long id);

    ProductEntity getById(Long id);

    void add(ProductAddServiceModel productAddServiceModel) throws IOException;

    List<ProductViewModel> getAll();

    List<ProductViewModel> getTheNewestEight();

    List<ProductViewModel> getByCategory(ProductCategoryEnum category);

    void deleteById(Long id);

    ProductEntity getByName(String name);

    void update(ProductUpdateServiceModel productUpdateServiceModel);

}
