package com.ponomarenko.acservice.repository;

import com.ponomarenko.acservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
