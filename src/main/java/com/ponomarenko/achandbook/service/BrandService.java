package com.ponomarenko.achandbook.service;

import com.ponomarenko.achandbook.model.Brand;
import com.ponomarenko.achandbook.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand findById(Long id){
        return brandRepository.getOne(id);
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
