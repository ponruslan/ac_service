package com.ponomarenko.acservice.repository;

import com.ponomarenko.acservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
