package com.example.project.controller;

import com.example.project.order.OrderRequest;
import com.example.project.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/showAllOrderIds")
    @PreAuthorize("hasAuthority('user:read')")
    public List<Integer> showAllOrders() {
        return orderService.showAllOrderIds();
    }

    @GetMapping("/showAllOrderDetails/{orderId}")
    @PreAuthorize("hasAuthority('user:read')")
    public List<String> showAllOrderDetailsByOrderId(@PathVariable Integer orderId) {
        return orderService.showAllOrderDetails(orderId);
    }

    @PostMapping("/createOrder")
    @PreAuthorize("hasAuthority('user:update')")
    public void createOrder(Principal principal) {
        orderService.createOrder(principal);
    }

    @PostMapping("/addProductToOrder")
    @PreAuthorize("hasAuthority('user:update')")
    public void addProductToOrder(@RequestBody OrderRequest orderRequest) {
        orderService.addProductToOrder(orderRequest);
    }
}
