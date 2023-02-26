package com.fitshop.web;

import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;
import com.fitshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersRestController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all/api")
    public ResponseEntity<List<OrderViewModel>> getAllOrders() {
        List<OrderViewModel> orders = this.orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user")
    public ResponseEntity<List<OrderProfileViewModel>> getUserOrders(@AuthenticationPrincipal User user) {
        List<OrderProfileViewModel> userOrders = this.orderService.getUserOrdersByUsername(user.getUsername());
        return ResponseEntity.ok(userOrders);
    }

}

