package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.Address;
import com.ecommerce.springbootecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByUser(User user);

}
