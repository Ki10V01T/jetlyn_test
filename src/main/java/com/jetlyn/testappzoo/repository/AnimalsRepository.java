package com.jetlyn.testappzoo.repository;

import com.jetlyn.testappzoo.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AnimalsRepository extends JpaRepository<Animals, UUID> {
    
}