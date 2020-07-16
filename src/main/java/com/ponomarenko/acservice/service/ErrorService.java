package com.ponomarenko.acservice.service;

import com.ponomarenko.acservice.model.Error;
import com.ponomarenko.acservice.repository.ErrorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

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
}
