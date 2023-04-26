package com.example.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermicroservice.entity.PhoneEntity;

@Repository
public interface RepositoryPhone extends JpaRepository<PhoneEntity, Long> {
    
}
