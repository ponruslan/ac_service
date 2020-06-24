package com.ponomarenko.achandbook.repository;

import com.ponomarenko.achandbook.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error,Long> {
}
