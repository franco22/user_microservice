package com.example.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.repository.RepositoryUser;

import lombok.AllArgsConstructor;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceUserImpl implements ServiceUser{

    @Autowired
    private final RepositoryUser repositoryUser;

    @Override
    public UserEntity saveUser(UserEntity user) {
    
        return repositoryUser.save(user);
    }

    @Override
    public UserEntity getUser(String email) {

        return repositoryUser.findByEmail(email);
    }

    @Override
    public UserEntity getUserById(UUID id) {

        return repositoryUser.findById(id).orElseThrow();
    }

    @Override
    public void deleteUser(UUID id) {

        repositoryUser.deleteById(id);
    }
    
}
