package com.example.fitshop.web;

import com.example.fitshop.model.view.OrderProfileViewModel;
import com.example.fitshop.model.view.OrderViewModel;
import com.example.fitshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final OrderService orderService;

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderViewModel>> getAllOrders() {
        List<OrderViewModel> orders = this.orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user")
    public ResponseEntity<List<OrderProfileViewModel>> getUserOrders(Principal principal) {
        List<OrderProfileViewModel> userOrders = this.orderService.getUserOrdersByUsername(principal.getName());
        return ResponseEntity.ok(userOrders);
    }

}

