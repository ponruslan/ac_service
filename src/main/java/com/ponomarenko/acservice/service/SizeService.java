package com.ponomarenko.acservice.service;

import com.ponomarenko.acservice.model.Size;
import com.ponomarenko.acservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public Size findById(Long id){
        return sizeRepository.getOne(id);
    }

    public List<Size> findAll(){
        return sizeRepository.findAll();
    }

    public Size saveSize(Size size){
        return sizeRepository.save(size);
    }

    public void deleteById(Long id){
        sizeRepository.deleteById(id);
    }
}
