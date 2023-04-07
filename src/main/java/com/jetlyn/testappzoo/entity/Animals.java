package com.jetlyn.testappzoo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="animals")
public class Animals implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;
    
    @Column(name="type")
    private String type;

    @Column(name="is_predator")
    private Boolean isPredator;

    public void setId(UUID id) {
        this.id = id;
    }   
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsPreadtor(Boolean isPredator) {
        this.isPredator = isPredator;
    } 

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Boolean getIsPredator() {
        return this.isPredator;
    }
}
