package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
