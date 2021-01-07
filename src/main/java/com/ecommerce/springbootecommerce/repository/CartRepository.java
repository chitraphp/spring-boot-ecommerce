package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findByEmail(String email);

    Cart findByCartIdAndEmail(int cartId, String email);

    void deleteByCartIdAndEmail(int cartId, String email);

    List<Cart> findAllByEmail(String email);

    List<Cart> findAllByOrderId(int orderId);
}
