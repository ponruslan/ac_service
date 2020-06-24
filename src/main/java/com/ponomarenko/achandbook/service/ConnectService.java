package com.ponomarenko.achandbook.service;

import com.ponomarenko.achandbook.model.Connect;
import com.ponomarenko.achandbook.repository.ConnectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectService {

    @Autowired
    private ConnectRepository connectRepository;

    public Connect findById(Long id){
        return connectRepository.getOne(id);
    }

    public List<Connect> findAll(){
        return connectRepository.findAll();
    }

    public Connect saveConnect(Connect connect){
        return connectRepository.save(connect);
    }

    public void deleteById(Long id){
        connectRepository.deleteById(id);
    }
}
