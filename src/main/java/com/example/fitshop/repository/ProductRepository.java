package com.example.fitshop.repository;

import com.example.fitshop.enums.ProductCategoryEnum;
import com.example.fitshop.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.isOrdered = FALSE ORDER BY p.price")
    List<ProductEntity> findTheCheapest();

    @Query("SELECT p FROM ProductEntity p WHERE p.isOrdered = FALSE ORDER BY p.created DESC")
    List<ProductEntity> findTheNewest();

    @Query("SELECT p FROM ProductEntity p WHERE p.category = :category AND p.isOrdered = FALSE")
    List<ProductEntity> findByCategory(ProductCategoryEnum category);

    @Query("SELECT p FROM ProductEntity p WHERE p.name = :name AND p.isOrdered = FALSE")
    ProductEntity getByName(String name);

    @Override
    @Query("SELECT p FROM ProductEntity p WHERE p.isOrdered = FALSE")
    List<ProductEntity> findAll();

    @Override
    @Query("SELECT p FROM ProductEntity p WHERE p.id = :id AND p.isOrdered = FALSE")
    Optional<ProductEntity> findById(Long id);
}

