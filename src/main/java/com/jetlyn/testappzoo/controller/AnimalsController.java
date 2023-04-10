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

/**
 * REST контроллер для сущности Animals (Животные)
 */
@RestController
public class AnimalsController {
    
    @Autowired
    private AnimalsService animalsService;
    
    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }

    /**
     * Endpoint, обслуживающий метод создания нового животного.
     * @param animal Сущность БД, таблица Animals (животные).
     * Обязательные поля для передачи запроса:
     * {"name":"string" (Unique),"type":"string" (FK), "isPredator":"bool"}
     * @return статус 201 в случае успешного создания записи в таблице Animals.
     * А также UUID созданной записи.
     * 409 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see AnimalsServiceImpl#create(Animals animal);
     */    
    @PostMapping(value = "/animals/new", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Animals animal) {
        final boolean created = animalsService.create(animal);

        return created
            ? new ResponseEntity<>(animal.getId(), HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    
    /**
     * Endpoint, обслуживающий метод чтения всех животных из таблицы Animals.
     * @return статус 200 в случае успешного чтения записей их таблицы Animals
     * А также, список найденных строк.
     * 404 - если записей в таблице не найдено
     * @see AnimalsServiceImpl#readAll();
     */ 
    @GetMapping(value = "/animals/get/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Animals>> readAll() {
        final List<Animals> animals = animalsService.readAll();

        return animals != null && !animals.isEmpty()
            ? new ResponseEntity<>(animals, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    /**
     * Endpoint, обслуживающий метод чтения одного животного из таблицы Animals по id.
     * @param animal Сущность БД, таблица Animals (животные).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK)}
     * @return статус 302 в случае, если запись в таблице Animals найдена.
     * А также, строку из таблицы Animals по искомому id.
     * 404 - в случае, если запись с искомым id не найдена.
     * @see AnimalsServiceImpl#read(UUID id);
     */ 
    @GetMapping(value = "/animals/get/one_by_id", produces = "application/json")
    public ResponseEntity<Animals> read(@RequestBody Animals animal) {
        final Animals founded = animalsService.read(animal.getId());
    
        return founded != null
            ? new ResponseEntity<>(founded, HttpStatus.FOUND)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод удаления животного по id
     * @param animal Сущность БД, таблица Animals (животные).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK)}
     * @return статус 200 в случае успешного удаления записи из таблицы Animals
     * 304 - в случае, если запись по id не была найдена
     * @see AnimalsServiceImpl#delete(UUID id);
     */ 
    @DeleteMapping(value = "/animals/delete/one_by_id")
    public ResponseEntity<?> delete(@RequestBody Animals animal) {
        final boolean deleted = animalsService.delete(animal.getId());
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    
    /**
     * Endpoint, обслуживающий метод удаления нескольких животных по списку id
     * @param animalList Список сущностей БД, таблица Animals (животные).
     * Обязательные поля для передачи запроса:
     * [{"id":"UUID" (PK)}, {"id":"UUID" (PK)}]
     * @return статус 200 в случае успешного удаления записей из таблицы Animals
     * 304 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see AnimalsServiceImpl#deleteMulti(List<Animals> animalsList);
     */ 
    @DeleteMapping(value = "/animals/delete/multi_by_id")
    public ResponseEntity<?> deleteMulti(@RequestBody List<Animals> animalsList) {
        final boolean deleted = animalsService.deleteMulti(animalsList);
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    
    /**
     * Endpoint, обслуживающий метод удаления всех животных из таблицы
     * @return статус 200 в случае успешного удаления записей из таблицы Animals
     * 304 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see AnimalsServiceImpl#deleteAll(List<Animals> animalsList);
     */ 
    @DeleteMapping(value = "/animals/all")
    public ResponseEntity<?> deleteAll() {
        final boolean deleted = animalsService.deleteAll();
     
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
