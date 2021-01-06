package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Place_order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OderRepository extends JpaRepository<Place_order,Long> {
    Place_order findByOrderId(int orderId);

    List<Place_order> findAll();
}
