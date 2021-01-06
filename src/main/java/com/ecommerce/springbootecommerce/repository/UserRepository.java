package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPasswordAndUsertype(String email, String password, String usertype);

    Optional<User> findByUsername(String username);
}
