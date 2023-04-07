package com.jetlyn.testappzoo.service;

import java.util.List;
import java.util.UUID;

import com.jetlyn.testappzoo.entity.Animals;

public interface AnimalsService {
    /**
     * Создает новое животное
     * @param client - животное для создания
     */
    Boolean create(Animals animal);
 
    /**
     * Возвращает список всех имеющихся животных
     * @return список животных
     */
    List<Animals> readAll();
 
    /**
     * Возвращает животное по его ID
     * @param id - ID животного
     * @return - объект пользователя с заданным ID
     */
    Animals read(UUID id);

    /**
     * Обновляет животное с заданным ID.
     * @param animal - животное в соответсвии с которым нужно обновить данные
     * @param id - id животного которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    //Boolean update(Animals animal, String name);
 
    /**
     * Удаляет нескольких животных по заданным ID
     * @param idList - список id животных, которые нужно удалить
     * @return - true если животные были удалены, иначе false
     */
    Boolean deleteMulti(List<UUID> idList);

    /**
     * Удаляет одно животное.
     * @param id - идентификатор удаляемого животного
     * @return
     */
    Boolean delete(UUID id);

    /**
     * Удаляет всех животных
     * @return
     */
    Boolean deleteAll();
}
