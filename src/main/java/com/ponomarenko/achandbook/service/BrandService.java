package com.ponomarenko.achandbook.service;

import com.ponomarenko.achandbook.model.Brand;
import com.ponomarenko.achandbook.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
