package com.example.FastFoodAPI.services;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.entities.Status;
import com.example.FastFoodAPI.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public OrderService() {
    }
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order createOrder(Order order) {
        LocalDateTime localDate = LocalDateTime.now();
        order.setUpdatedAt(localDate);
        order.setCreatedAt(localDate);
        return orderRepository.save(order);
    }
    public ArrayList<Order> getOrders() {
        return (ArrayList<Order>) orderRepository.findAll();
    }
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new RuntimeException("Order id '"+id+"' not found");
        }
        return order.get();
    }
    public Order updateStatus(Long id, Status status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }
    public Order updateNotes(Long id, String note) {
        Order order = getOrderById(id);
        order.setNote(note);
        orderRepository.save(order);
        return order;
    }
}
