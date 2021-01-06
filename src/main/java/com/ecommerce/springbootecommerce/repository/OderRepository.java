package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Place_order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderRepository extends JpaRepository<Place_order,Long> {
}
