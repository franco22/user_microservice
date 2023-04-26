package com.example.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermicroservice.entity.PhoneEntity;
import com.example.usermicroservice.repository.RepositoryPhone;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePhoneImpl implements ServicePhone {

    @Autowired
    private RepositoryPhone repositoryPhone;

    @Override
    public PhoneEntity savePhone(PhoneEntity phone) {
        
        return repositoryPhone.save(phone);
    }
    
}
