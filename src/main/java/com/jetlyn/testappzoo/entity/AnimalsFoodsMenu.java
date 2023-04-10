package com.jetlyn.testappzoo.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "animals_foods_menu")
public class AnimalsFoodsMenu implements Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="animal_name")
    private String animalName;

    @Column(name="food_name")
    private String foodName;

    @Column(name="per_day")
    private Double perDay;
}
