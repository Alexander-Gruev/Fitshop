package com.example.fitshop.repository;

import com.example.fitshop.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o.productName FROM OrderEntity o WHERE o.id = :id")
    String getNameById(Long id);

    List<OrderEntity> findAllByClient_Id(Long clientId);

    List<OrderEntity> findAllByClient_Username(String username);

}
