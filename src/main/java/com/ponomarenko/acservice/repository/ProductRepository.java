package com.ponomarenko.acservice.repository;

import com.ponomarenko.acservice.model.Brand;
import com.ponomarenko.acservice.model.Product;
import com.ponomarenko.acservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrand_Id(Long id);

    List<Product> findByBrandAndType(Brand brand, Type type);
}
