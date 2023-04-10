package com.jetlyn.testappzoo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jetlyn.testappzoo.entity.AnimalsFoodsMenu;
import com.jetlyn.testappzoo.entity.AnimalsFoodsMenuAggregator;
import com.jetlyn.testappzoo.entity.Foods;
import com.jetlyn.testappzoo.service.FoodsService;

@RestController
public class FoodsController {
    //about 7 days menu

    @Autowired
    private FoodsService foodsService;

    public FoodsController(FoodsService foodsService) {
        this.foodsService = foodsService;
    }

    @PostMapping(value = "/foods/new", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UUID> create(@RequestBody Foods food) {
        final boolean created = foodsService.create(food);
        
        return created
                ? new ResponseEntity<>(food.getId(), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/foods/get/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Foods>> readAll() {
        final List<Foods> animals = foodsService.readAll();

        return animals != null && !animals.isEmpty()
                ? new ResponseEntity<>(animals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/foods/get/one_by_id", produces = "application/json")
    public ResponseEntity<Foods> read(@RequestBody Foods food) {
        final Foods founded = foodsService.read(food.getId());

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/foods/get/all_menu_on_all_week")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readMenuforAllAnimalsOnAllWeek() {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readMenuforAllAnimalsOnAllWeek();

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/foods/get/menu_by_animal_name")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readMenuforSpecificAnimalPerWeek(@RequestBody AnimalsFoodsMenu afMenu) {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readMenuforSpecificAnimalPerWeek(afMenu.getAnimalName());

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/foods/get/all_menu_extended", produces = "application/json")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readExtendedAnimalMenu() {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readExtendedAnimalMenu();

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/foods/update/one/left_by_id", produces = "application/json")
    public ResponseEntity<Integer> updateFoodsLeftById(@RequestBody Foods food) {
        final Integer updated = foodsService.updateFoodsLeftById(food.getId(), food.getLeft());

        return updated > 0
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/foods/update/one/animal_menu", produces = "application/json")
    public ResponseEntity<Integer> updateConsumeForSpecificAnimalPerDay(@RequestBody AnimalsFoodsMenu afmenu) {
        final Integer updated = foodsService.updateConsumeForSpecificAnimalPerDay(afmenu.getAnimalName(), 
                afmenu.getFoodName(), afmenu.getPerDay());

        return updated > 0
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/foods/delete/one_by_id")
    public ResponseEntity<?> delete(@RequestBody Foods food) {
        final boolean deleted = foodsService.delete(food.getId());

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/foods/delete/multi_by_id")
    public ResponseEntity<?> deleteMulti(@RequestBody List<Foods> foodsList) {
        final boolean deleted =  foodsService.deleteMulti(foodsList);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/foods/delete/all")
    public ResponseEntity<?> deleteAll() {
        final boolean deleted = foodsService.deleteAll();

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
