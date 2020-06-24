package com.ponomarenko.achandbook.repository;

import com.ponomarenko.achandbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
