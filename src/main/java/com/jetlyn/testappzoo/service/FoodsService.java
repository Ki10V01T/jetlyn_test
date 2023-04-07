package com.jetlyn.testappzoo.service;

import java.util.List;
import java.util.UUID;

import com.jetlyn.testappzoo.entity.Foods;

public interface FoodsService {
    /**
     * Создает новое животное
     * @param client - животное для создания
     */
    Boolean create(Foods food);
 
    /**
     * Возвращает список всех имеющихся животных
     * @return список животных
     */
    List<Foods> readAll();
 
    /**
     * Возвращает животное по его ID
     * @param id - ID животного
     * @return - объект пользователя с заданным ID
     */
    Foods read(UUID id);
 
    /**
     * Обновляет животное с заданным ID.
     * @param animal - животное в соответсвии с которым нужно обновить данные
     * @param id - id животного которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    //Boolean update(Foods food, String name);
 
    /**
     * Удаляет животное с заданным ID
     * @param id - id животного, которого нужно удалить
     * @return - true если животное было удалено, иначе false
     */
    Boolean delete(UUID id);
}
