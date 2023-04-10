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
     * Получает список всех имеющихся продуктов
     * @return список продуктов
     */
    List<Foods> readAll();

    /**
     * Меняет количество продукта по его id.
     * @param id идентификатор продукта
     * @param newLeft новый остаток продукта
     * @return количество измененных строк
     */
    Integer updateFoodsLeftById(UUID id, Integer newLeft);

    /**
     * Менеяет ежедневную норму для животного по его имени.
     * @param animalname имя животного
     * @param foodName название продукта
     * @param newPerDay новая норма потребления для животного на день
     * @return количество измененных строк
     */
    Integer updateConsumeForSpecificAnimalPerDay(String animalname, String foodName, Double newPerDay);
 
    /**
     * Получает продукт по его ID
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

    /**
     * Получает информацию о животных, с перечислением продуктов их питания.
     * @return Список агрегированных таблиц
     */
    List<AnimalsFoodsMenuAggregator> readExtendedAnimalMenu();

    /**
     * Получает записи рациона питания для конкретного животного на неделю
     * @param animalname имя конкретного животного
     * @return Список агрегированных таблиц
     */
    List<AnimalsFoodsMenuAggregator> readMenuforSpecificAnimalPerWeek(String animalname);
    
    /**
     * Получает информацию о питании животных на 7 дней 
     * @return Список агрегированных таблиц
     */
    List<AnimalsFoodsMenuAggregator> readMenuforAllAnimalsOnAllWeek();
}
