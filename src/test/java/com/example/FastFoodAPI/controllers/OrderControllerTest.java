package com.example.FastFoodAPI.controllers;

import com.example.FastFoodAPI.entities.Order;
import com.example.FastFoodAPI.repository.OrderRepository;
import com.example.FastFoodAPI.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testCreateOrder() throws Exception {
        Order newOrder = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        newOrder.setId(1L);
        newOrder.setNote("Test Note");
        mvc.perform(post("/api/orders")
                .content(mapper.writeValueAsString(newOrder))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value(newOrder.getCustomerName()))
                .andExpect((jsonPath("$.createdAt").value(newOrder.getCreatedAt())))
                .andExpect((jsonPath("$.status").value(newOrder.getStatus())))
                .andExpect((jsonPath("$.description").value(newOrder.getDescription())))
                .andExpect((jsonPath("$.lastUpdatedAt").value(newOrder.getUpdatedAt())));

    }

    @Test
    void testGetAllOrders() throws Exception {
        Order order = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
//        when(orderService.getOrders()).thenReturn(new List(order))
        mvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById() throws Exception {
        Order order = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        order.setId(1L);
        when(orderService.getOrderById(order.getId())).thenReturn(order);
        mvc.perform(get("/api/orders/"+order.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName").value(order.getCustomerName()));
    }

    @Test
    void updateOrder() throws Exception {
        Order order = new Order("Hungry Man Jr", "Burger, double meat, extra pickles, doughnut buns, diet coke");
        order.setId(1L);
        when(orderService.getOrderById(order.getId())).thenReturn(order);
        when(orderService.createOrder(order)).thenReturn(order);
        String orderUrl = "/api/orders/"+order.getId();
        mvc.perform(patch(orderUrl)
                .param("status", "COMPLETED")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("COMPLETED")));
    }
}