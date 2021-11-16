package com.example.fitshop.repository;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p ORDER BY p.price")
    List<ProductEntity> findTheCheapest();

    @Query("SELECT p FROM ProductEntity p ORDER BY p.created DESC")
    List<ProductEntity> findTheNewest();

    List<ProductEntity> findByCategory(ProductCategoryEnum category);

    Long deleteByName(String name);

    ProductEntity getByName(String name);
}

