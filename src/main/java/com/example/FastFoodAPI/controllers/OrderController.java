package com.example.FastFoodAPI.controllers;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Order createOrder(@RequestBody Order order){
        return service.createOrder(order);
    }

    @GetMapping("/")
    public List<Order> getAllOrders(){
        return service.getOrders();
    }

}
