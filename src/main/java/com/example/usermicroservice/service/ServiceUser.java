package com.example.usermicroservice.service;

import com.example.usermicroservice.entity.UserEntity;

import java.util.UUID;


public interface ServiceUser {
    UserEntity saveUser(UserEntity user);

    UserEntity getUser(String email);

    UserEntity getUserById(UUID id);

    void deleteUser(UUID id);
}
