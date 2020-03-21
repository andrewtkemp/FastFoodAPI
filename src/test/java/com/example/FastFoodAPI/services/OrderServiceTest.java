package com.example.FastFoodAPI.services;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.entities.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() throws Exception {
        Order order = orderService.createOrder("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        assertNotNull(order.getId());
    }
    @Test
    void getAllOrders() throws Exception {
        List<Order> orders = orderService.getOrders();
        assertNotNull(orders.size() > 0);
    }
    @Test
    void getOrderById() throws Exception {
        Order order = orderService.createOrder("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        Order sameOrder = orderService.getOrderById(order.getId());
        assertEquals(order.getId(), sameOrder.getId());
    }
    @Test
    void updateOrderStatus() throws Exception {
        Order order = orderService.createOrder("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        orderService.updateStatus(order.getId(), Status.CANCELLED);
        assertEquals(Status.CANCELLED, order.getStatus());
    }
    @Test
    void updateOrdernote() throws Exception {
        String note = "Test Note";
        Order order = orderService.createOrder("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        orderService.updateNotes(order.getId(), note);
        assertEquals(note, order.getNote());
    }
}