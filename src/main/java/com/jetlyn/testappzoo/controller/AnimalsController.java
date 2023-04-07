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

import com.jetlyn.testappzoo.entity.Animals;
import com.jetlyn.testappzoo.service.AnimalsService;

@RestController
public class AnimalsController {
    private final AnimalsService animalsService;

    @Autowired
    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }

    @PostMapping(value = "/animals", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Animals animal) {
        final boolean created = animalsService.create(animal);
        return created
            ? new ResponseEntity<>(animal.getId(), HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/animals/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Animals>> readAll() {
           final List<Animals> animals = animalsService.readAll();

       return animals != null && !animals.isEmpty()
               ? new ResponseEntity<>(animals, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //?????
    @GetMapping(value = "/animals/{id}", produces = "application/json")
    public ResponseEntity<Animals> read(@RequestBody Animals animal) {
           final Animals founded = animalsService.read(animal.getId());

       return founded != null
               ? new ResponseEntity<>(animal, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/animals/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Animals>> read() {
           final List<Animals> animals = animalsService.readAll();

       return animals != null && !animals.isEmpty()
               ? new ResponseEntity<>(animals, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*ZZz@GetMapping(value = "/users")
    public ResponseEntity<Users> read(@RequestBody Users Oid) {
       final Users user = usersService.read(Oid.getId());

       return user != null
               ? new ResponseEntity<>(user, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    /*@PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Users user) {
       final boolean updated = usersService.update(user, id);

       return updated
               ? new ResponseEntity<>(HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/

    @DeleteMapping(value = "/animals")
    public ResponseEntity<?> delete(@RequestBody Animals animal) {
       final boolean deleted = animalsService.delete(animal.getId());

       return deleted
               ? new ResponseEntity<>(HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/animals/multi")
    public ResponseEntity<?> deleteMulti(@RequestBody List<UUID> delIds) {
       final boolean deleted = animalsService.deleteMulti(delIds);

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
