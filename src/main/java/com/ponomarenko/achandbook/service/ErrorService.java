package com.ponomarenko.achandbook.service;

import com.ponomarenko.achandbook.model.Error;
import com.ponomarenko.achandbook.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepository;

    public Error findById(Long id){
        return errorRepository.getOne(id);
    }

    public List<Error> findAll(){
        return errorRepository.findAll();
    }

    public Error saveError(Error error){
        return errorRepository.save(error);
    }

    public void deleteById(Long id){
        errorRepository.deleteById(id);
    }

    public void deleteAll(){
        errorRepository.deleteAll();
    }
}
