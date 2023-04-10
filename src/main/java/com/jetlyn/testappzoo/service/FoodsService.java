package com.jetlyn.testappzoo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jetlyn.testappzoo.entity.AnimalsFoodsMenuAggregator;
import com.jetlyn.testappzoo.entity.Foods;

@Service
public interface FoodsService {
    /**
     * Создает новый продукт
     * @param food - новый продукт
     * @return true - если успешно, иначе - false
     */
    Boolean create(Foods food);
 
    /**
     * Возвращает список всех имеющихся продуктов
     * @return список животных
     */
    List<Foods> readAll();

    //Foods updateFoodsLeftById(Foods food);
    Integer updateFoodsLeftById(UUID id, Integer newLeft);
    Integer updateConsumeForSpecificAnimalPerDay(String animalname, String foodName, Double newPerDay);
 
    /**
     * Возвращает продукт по его ID
     * @param id - ID животного
     * @return искомый продукт
     */
    Foods read(UUID id);
 
    /**
     * Обновляет продукт с заданным ID.
     * @param food - продукт в соответсвии с которым нужно обновить данные
     * @param id - id животного которого нужно обновить
     * @return - true если успешно, иначе false
     */
    //Boolean update(Foods food, String name);
 
    /**
     * Удаляет несколько продуктов по заданным ID
     * @param idList - список id продуктов, которые нужно удалить
     * @return true - если успешно, иначе - false
     */
    Boolean deleteMulti(List<Foods> idList);

    /**
     * Удаляет один продукт.
     * @param id - идентификатор удаляемого продукта
     * @return true - если успешно, иначе - false
     */
    Boolean delete(UUID id);

    /**
     * Удаляет все продукты
     * @return true - если успешно, иначе - false
     */
    Boolean deleteAll();

    List<AnimalsFoodsMenuAggregator> readExtendedAnimalMenu();
    //Map<?, ?> readMenuForSpecificAnimalPerDay(String animalName);
    List<AnimalsFoodsMenuAggregator> readMenuforSpecificAnimalPerWeek(String animalname);
    List<AnimalsFoodsMenuAggregator> readMenuforAllAnimalsOnAllWeek();
}
