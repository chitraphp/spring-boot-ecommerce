package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
