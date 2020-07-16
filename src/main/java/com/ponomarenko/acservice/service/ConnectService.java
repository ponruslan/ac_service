package com.ponomarenko.acservice.service;

import com.ponomarenko.acservice.model.Connect;
import com.ponomarenko.acservice.repository.ConnectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectService {

    private final ConnectRepository connectRepository;

    public ConnectService(ConnectRepository connectRepository) {
        this.connectRepository = connectRepository;
    }

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
