package com.jetlyn.testappzoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jetlyn.testappzoo.entity.Animals;
import com.jetlyn.testappzoo.service.AnimalsService;

@RestController
public class AnimalsController {
    
    @Autowired
    private AnimalsService animalsService;
    
    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }
    
    @PostMapping(value = "/animals/new", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Animals animal) {
        final boolean created = animalsService.create(animal);

        return created
            ? new ResponseEntity<>(animal.getId(), HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    
    @GetMapping(value = "/animals/get/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Animals>> readAll() {
        final List<Animals> animals = animalsService.readAll();

        return animals != null && !animals.isEmpty()
            ? new ResponseEntity<>(animals, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(value = "/animals/get/one_by_id", produces = "application/json")
    public ResponseEntity<Animals> read(@RequestBody Animals animal) {
        final Animals founded = animalsService.read(animal.getId());
    
        return founded != null
            ? new ResponseEntity<>(founded, HttpStatus.FOUND)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping(value = "/animals/delete/one_by_id")
    public ResponseEntity<?> delete(@RequestBody Animals animal) {
        final boolean deleted = animalsService.delete(animal.getId());
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    
    @DeleteMapping(value = "/animals/delete/multi_by_id")
    public ResponseEntity<?> deleteMulti(@RequestBody List<Animals> animalsList) {
        final boolean deleted = animalsService.deleteMulti(animalsList);
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    
    @DeleteMapping(value = "/animals/all")
    public ResponseEntity<?> deleteAll() {
        final boolean deleted = animalsService.deleteAll();
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
