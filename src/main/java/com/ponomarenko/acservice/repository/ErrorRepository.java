package com.ponomarenko.acservice.repository;

import com.ponomarenko.acservice.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Long> {
}
