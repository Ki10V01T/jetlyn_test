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

/**
 * REST контроллер для сущности Foods
 */
@RestController
public class FoodsController {
    //about 7 days menu

    @Autowired
    private FoodsService foodsService;

    public FoodsController(FoodsService foodsService) {
        this.foodsService = foodsService;
    }

    /**
     * Endpoint, обслуживающий метод создания нового продукта
     * @param food Сущность БД Foods (продукты).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK),"name":"string" (Unique), "left":"integer", "measurement_unit":"string","type":"string"}
     * @return статус 201 в случае успешного создания записи в таблице Foods
     * А также, UUID созданной записи.
     * 409 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see FoodsServiceImpl#create(Foods food);
     */ 
    @PostMapping(value = "/foods/new", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UUID> create(@RequestBody Foods food) {
        final boolean created = foodsService.create(food);
        
        return created
                ? new ResponseEntity<>(food.getId(), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint, обслуживающий метод чтения всех продуктов из таблицы Foods
     * @return статус 200 в случае успешного чтения
     * А также, список записей таблицы Foods
     * 404 - в случае, если записи в таблице не найдены 
     * @see FoodsServiceImpl#readAll();
     */ 
    @GetMapping(value = "/foods/get/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Foods>> readAll() {
        final List<Foods> animals = foodsService.readAll();

        return animals != null && !animals.isEmpty()
                ? new ResponseEntity<>(animals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод чтения по id из таблицы Foods
     * @param food Сущность БД, таблица Foods (продукты).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK)"}
     * @return статус 302 в случае если запись найдена в таблице Foods
     * А также, найденную по id, строку в таблице
     * 404 - в случае если запись не найдена
     * @see FoodsServiceImpl#read(UUID id);
     */ 
    @GetMapping(value = "/foods/get/one_by_id", produces = "application/json")
    public ResponseEntity<Foods> read(@RequestBody Foods food) {
        final Foods founded = foodsService.read(food.getId());

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод получения информации о питании животных на 7 дней.
     * @return статус 302 в случае, если записи найдены в таблице 
     * А также, аггрегированную запись из нескольких таблиц,
     * соответствующих структуре AnimalsFoodsMenuAggregator
     * 404 - в случае если записи не найдены
     * @see FoodsServiceImpl#readMenuforAllAnimalsOnAllWeek();
     */ 
    @GetMapping(value = "/foods/get/all_menu_on_all_week")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readMenuforAllAnimalsOnAllWeek() {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readMenuforAllAnimalsOnAllWeek();

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод чтения записей рациона питания для конкретного животного на неделю
     * @param afmenu Сущность БД, таблица AnimalsFoodsMenu (Рацион питания животных).
     * Обязательные поля для передачи запроса:
     * {"name":"string" (Unique)"}
     * @return статус 302 в случае если запись найдена в таблице Foods
     * А также, найденный по id, агрегированный список строк
     * 404 - в случае если запись не найдена
     * @see FoodsServiceImpl#readMenuforSpecificAnimalPerWeek(String animalName);
     */ 
    @GetMapping(value = "/foods/get/menu_by_animal_name")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readMenuforSpecificAnimalPerWeek(@RequestBody AnimalsFoodsMenu afMenu) {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readMenuforSpecificAnimalPerWeek(afMenu.getAnimalName());

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод получегия информации о животных, с перечислением продуктов их питания.
     * @return статус 302 в случае если запись найдена в таблице Foods
     * А также, найденный по id, агрегированный список строк
     * 404 - в случае если запись не найдена
     * @see FoodsServiceImpl#readExtendedAnimalMenu();
     */ 
    @GetMapping(value = "/foods/get/all_menu_extended", produces = "application/json")
    public ResponseEntity<List<AnimalsFoodsMenuAggregator>> readExtendedAnimalMenu() {
        final List<AnimalsFoodsMenuAggregator> founded = foodsService.readExtendedAnimalMenu();

        return founded != null
                ? new ResponseEntity<>(founded, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint, обслуживающий метод изменения количества продукта по его id.
     * @param food Сущность БД, таблица Foods (Продукты).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK), "left":"integer"}
     * @return статус 200 в случае если запись обновлена в таблице Foods
     * А также, список измененных строк.
     * 304 - в случае если запись не найдена или нарушена целостность таблицы
     * @see FoodsServiceImpl#updateFoodsLeftById(UUID id, Integer left);
     */ 
    @PostMapping(value = "/foods/update/one/left_by_id", produces = "application/json")
    public ResponseEntity<Integer> updateFoodsLeftById(@RequestBody Foods food) {
        final Integer updated = foodsService.updateFoodsLeftById(food.getId(), food.getLeft());

        return updated > 0
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint, обслуживающий метод изменения ежедневной нормы для животного по его имени (unique).
     * @param afmenu Сущность БД, таблица AnimalsFoodsMenu (Рацион питания животныъ).
     * Обязательные поля для передачи запроса:
     * {"animalName":"string" (Unique), "foodName":"string", "perDay":"double"}
     * @return статус 200 в случае если запись обновлена в таблице AnimalsFoodsMenu
     * А также, список измененных строк.
     * 304 - в случае если запись не найдена или нарушена целостность таблицы
     * @see FoodsServiceImpl#updateConsumeForSpecificAnimalPerDay(String animalName, String foodName, Double newPerDay);
     */ 
    @PostMapping(value = "/foods/update/one/animal_menu", produces = "application/json")
    public ResponseEntity<Integer> updateConsumeForSpecificAnimalPerDay(@RequestBody AnimalsFoodsMenu afmenu) {
        final Integer updated = foodsService.updateConsumeForSpecificAnimalPerDay(afmenu.getAnimalName(), 
                afmenu.getFoodName(), afmenu.getPerDay());

        return updated > 0
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint, обслуживающий метод удаления продукта по id
     * @param food Сущность БД, таблица Foods (продукты).
     * Обязательные поля для передачи запроса:
     * {"id":"UUID" (PK)}
     * @return статус 200 в случае успешного удаления записи из таблицы Foods
     * 304 - в случае, если запись по id не была найдена
     * @see FoodsServiceImpl#delete(UUID id);
     */ 
    @DeleteMapping(value = "/foods/delete/one_by_id")
    public ResponseEntity<?> delete(@RequestBody Foods food) {
        final boolean deleted = foodsService.delete(food.getId());

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint, обслуживающий метод удаления нескольких продуктов по списку id
     * @param foodsList Список сущностей БД, таблица Foods (продукты).
     * Обязательные поля для передачи запроса:
     * [{"id":"UUID" (PK)}, {"id":"UUID" (PK)}]
     * @return статус 200 в случае успешного удаления записей из таблицы Animals
     * 304 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see FoodsServiceImpl#deleteMulti(List<Foods> foodsList);
     */ 
    @DeleteMapping(value = "/foods/delete/multi_by_id")
    public ResponseEntity<?> deleteMulti(@RequestBody List<Foods> foodsList) {
        final boolean deleted =  foodsService.deleteMulti(foodsList);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint, обслуживающий метод удаления всех продуктов из таблицы
     * @return статус 200 в случае успешного удаления записей из таблицы Foods
     * 304 - в случае невалидного значения/нарушения ограничений целостности таблицы
     * @see FoodsServiceImpl#deleteAll(List<Foods> foodsList);
     */ 
    @DeleteMapping(value = "/foods/delete/all")
    public ResponseEntity<?> deleteAll() {
        final boolean deleted = foodsService.deleteAll();

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
