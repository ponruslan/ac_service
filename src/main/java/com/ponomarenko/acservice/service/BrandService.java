package com.ponomarenko.acservice.service;

import com.ponomarenko.acservice.model.Brand;
import com.ponomarenko.acservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    public Brand saveBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public void deleteById(Long id){
        brandRepository.deleteById(id);
    }
}
