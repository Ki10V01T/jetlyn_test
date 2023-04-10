package com.jetlyn.testappzoo.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name="animals")
public class Animals implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="name")
    private String name;
    
    @Column(name="type")
    private String type;

    @Column(name="is_predator")
    private Boolean isPredator;
}
