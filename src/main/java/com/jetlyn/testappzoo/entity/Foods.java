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
@Table(name="foods")
public class Foods implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="left")
    private Integer left;

    @Column(name="measurement_units")
    private String measurementUnits;

    @Column(name="type")
    private String type;


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public void setMeasurementUnits(String measurementUnits) {
        this.measurementUnits = measurementUnits;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLeft() {
        return this.left;
    }
    
    public String getMeasurementUnits() {
        return this.measurementUnits;
    }

    public String getType() {
        return this.type;
    }

}
