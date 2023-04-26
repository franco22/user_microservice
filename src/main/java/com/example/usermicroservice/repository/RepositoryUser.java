package com.example.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermicroservice.entity.UserEntity;

import java.util.UUID;

@Repository
public interface RepositoryUser extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);
}
