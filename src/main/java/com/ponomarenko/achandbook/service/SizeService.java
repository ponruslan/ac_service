package com.ponomarenko.achandbook.service;

import com.ponomarenko.achandbook.model.Size;
import com.ponomarenko.achandbook.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

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
