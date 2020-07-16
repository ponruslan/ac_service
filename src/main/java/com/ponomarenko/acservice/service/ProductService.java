package com.ponomarenko.acservice.service;

import com.ponomarenko.acservice.model.Brand;
import com.ponomarenko.acservice.model.Product;
import com.ponomarenko.acservice.model.Type;
import com.ponomarenko.acservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id){
        return productRepository.getOne(id);
    }

    public List<Product> findByBrandId(Long id){
        return productRepository.findByBrand_Id(id)
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public List<Product> findAll(){
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public List<Product> findByBrandAndType(Brand brand, Type type){
        return productRepository.findByBrandAndType(brand,type)
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void addProductsFromFile(Brand brand, Type type, MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String productName;
        while ((productName = reader.readLine()) != null) {
            Product product = new Product();
            product.setName(productName.trim());
            product.setBrand(brand);
            product.setType(type);
            saveProduct(product);
        }
    }

}
