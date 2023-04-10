package com.jetlyn.testappzoo.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedNativeQueries;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.UUID;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Foods.updateFoodsLeftById",
        query = "update Foods f" 
        + " set \"left\" = :newLeft" 
        + " where f.id = :id" 
    ),
    @NamedNativeQuery(
        name = "Foods.readExtendedAnimalMenu",
        query = "select animals_foods_menu.animal_name," 
        + " animals.\"type\","
        + " animals.is_predator,"
        + " animals_foods_menu.food_name,"
        + " animals_foods_menu.per_day,"
        + " foods.measurement_unit,"
        + " foods.\"left\"," 
        + " foods.\"left\"-animals_foods_menu.per_day as \"diff\""
        + " from animals_foods_menu"
        + " join foods on foods.\"name\"=animals_foods_menu.food_name"
        + " join animals on animals_foods_menu.animal_name=animals.\"name\""
    ),
    @NamedNativeQuery(
        name = "Foods.updateConsumeForSpecificAnimalPerDay",
        query = "update animals_foods_menu set per_day = :value"
        + " where animal_name = :animalName and food_name = :foodName"
    ),
    @NamedNativeQuery(
        name = "Foods.readMenuforSpecificAnimalPerWeek",
        query = "select animals_foods_menu.food_name,"
        + " animals_foods_menu.per_day*7 as \"menu_on_week\","
        + " foods.\"left\" as \"week_consumption\","
        + " foods.\"left\"-animals_foods_menu.per_day*7 as \"diff\""
        + " from animals_foods_menu"
        + " join foods on foods.\"name\"=animals_foods_menu.food_name"
        + " where animals_foods_menu.animal_name = :animalName"
    ),
    @NamedNativeQuery(
        name = "Foods.readMenuforAllAnimalsOnAllWeek",
        query = "select animals_foods_menu.animal_name,"
        + " animals_foods_menu.food_name,"
        + " animals_foods_menu.per_day*7 as \"menu_on_week\","
        + " foods.\"left\" as \"total\","
        + " foods.\"left\"-animals_foods_menu.per_day*7 as \"diff\""
        + " from animals_foods_menu"
        + " join foods on foods.\"name\"=animals_foods_menu.food_name;"
    )
})
@Entity
@Getter
@Setter
@Table(name="foods")
public class Foods implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="left")
    private Integer left;

    @Column(name="measurement_units")
    private String measurementUnits;

    @Column(name="type")
    private String type;
}
