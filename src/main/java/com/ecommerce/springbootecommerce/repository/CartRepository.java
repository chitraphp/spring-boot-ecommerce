package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Cart;
import com.ecommerce.springbootecommerce.model.Cart1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart1> findByEmail(String email);

    Cart1 findByBufcartIdAndEmail(int cartId, String email);

    void deleteByBufcartIdAndEmail(int cartId, String email);

    List<Cart1> findAllByEmail(String email);

    List<Cart1> findAllByOrderId(int orderId);
}
