package com.example.FastFoodAPI.services;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.entities.Status;
import com.example.FastFoodAPI.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Test
    void createOrder() throws Exception {
        Order newOrder = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        newOrder.setStatus(Status.PENDING);
        newOrder.setNote("New Note");
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setUpdatedAt(LocalDateTime.now());
        Order order = orderService.createOrder(newOrder);
        Order savedOrder = orderService.getOrderById(order.getId());
        assertEquals(savedOrder.getNote(), "New Note");
    }
    @Test
    void getAllOrders() throws Exception {
        List<Order> orders = orderService.getOrders();
        assertNotNull(orders.size() > 0);
    }
    @Test
    void getOrderById() throws Exception {
        Order newOrder = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        Order order = orderService.createOrder(newOrder);
        Order sameOrder = orderService.getOrderById(order.getId());
        assertEquals(order.getId(), sameOrder.getId());
    }
    @Test
    void updateOrderStatus() throws Exception {
        Order newOrder = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        Order order = orderService.createOrder(newOrder);
        orderService.updateStatus(order.getId(), Status.CANCELLED);
        assertEquals(Status.CANCELLED, order.getStatus());
    }
    @Test
    void updateOrdernote() throws Exception {
        String note = "Test Note";
        Order newOrder = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        Order order = orderService.createOrder(newOrder);
        orderService.updateNotes(order.getId(), note);
        assertEquals(note, order.getNote());
    }
}