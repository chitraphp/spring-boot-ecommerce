package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductid(int productid);

    void deleteByProductid(int productid);

    List<Product> findAll();
}
