package com.example.FastFoodAPI.services;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.entities.Status;
import com.example.FastFoodAPI.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order createOrder(String customerName, String description) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setDescription(description);
        order.setStatus(Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
    public List<Order> getOrders() {
        return orderRepository.findAll();
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
