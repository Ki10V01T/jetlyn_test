package com.jetlyn.testappzoo.repository;

import com.jetlyn.testappzoo.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AnimalsRepository extends JpaRepository<Animals, UUID>, CrudRepository<Animals, UUID> {


}