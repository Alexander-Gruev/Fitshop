package com.example.fitshop.web;

import com.example.fitshop.model.view.OrderViewModel;
import com.example.fitshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final OrderService orderService;

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderViewModel>> getAllOrders() {
        List<OrderViewModel> orders = this.orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

}

