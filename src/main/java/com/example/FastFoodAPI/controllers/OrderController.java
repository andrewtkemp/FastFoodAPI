package com.example.FastFoodAPI.controllers;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.entities.Status;
import com.example.FastFoodAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<ArrayList<Order>> getAllOrders(){
        ArrayList<Order> orders = service.getOrders();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = service.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    @PatchMapping("/{id}")
    public  ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestParam Status status){
        Order order = service.getOrderById(id);
        order.setStatus(status);
        return ResponseEntity.ok(order);
    }

}
