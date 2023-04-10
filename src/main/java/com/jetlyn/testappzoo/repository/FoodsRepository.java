package com.jetlyn.testappzoo.repository;
import com.jetlyn.testappzoo.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface FoodsRepository extends JpaRepository<Foods, UUID>{
}
