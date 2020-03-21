package com.example.FastFoodAPI.repository;
import com.example.FastFoodAPI.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Long> {

}
