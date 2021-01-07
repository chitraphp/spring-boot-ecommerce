package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<PlaceOrder,Long> {
    PlaceOrder findByOrderId(int orderId);

    List<PlaceOrder> findAll();
}
