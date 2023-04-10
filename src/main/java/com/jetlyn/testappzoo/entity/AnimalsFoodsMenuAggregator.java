package com.jetlyn.testappzoo.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

/**
 * Сервисная, агрегирующая из нескольких таблиц - структура
 */
@Getter
@Setter
public class AnimalsFoodsMenuAggregator implements Serializable {
    
    @Column(name="animal_name")
    private String animalName;

    @Column(name="type")
    private String type;

    @Column(name="is_predator")
    private Boolean isPredator ;

    @Column(name="food_name")
    private String foodName;

    @Column(name="per_day")
    private Double perDay;

    @Column(name="measurement_unit")
    private String measurementUnit;

    @Column(name="left")
    private Integer left;

    @Column(name="diff")
    private Double diff;
}
