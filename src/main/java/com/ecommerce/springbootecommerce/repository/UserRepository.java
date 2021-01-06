package com.ecommerce.springbootecommerce.repository;

import com.ecommerce.springbootecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
