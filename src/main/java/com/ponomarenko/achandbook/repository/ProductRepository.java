package com.ponomarenko.achandbook.repository;

import com.ponomarenko.achandbook.model.Brand;
import com.ponomarenko.achandbook.model.Product;
import com.ponomarenko.achandbook.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByBrand_Id (Long id);
    List<Product> findByBrandAndType(Brand brand, Type type);
    List<Product> findByNameStartsWith(String prefix);
}
