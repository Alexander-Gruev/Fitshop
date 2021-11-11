package com.example.fitshop.service;

import com.example.fitshop.model.service.ProductServiceModel;
import com.example.fitshop.model.view.ProductDetailsViewModel;
import com.example.fitshop.model.view.ProductViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    void initProducts();

    List<ProductViewModel> getTheFourCheapest();

    ProductDetailsViewModel getById(Long id);

    void add(ProductServiceModel productServiceModel) throws IOException;

}
