package com.jetlyn.testappzoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jetlyn.testappzoo.entity.AnimalsFoodsMenuAggregator;
import com.jetlyn.testappzoo.entity.Foods;
import com.jetlyn.testappzoo.repository.FoodsRepository;

@Service
public class FoodsServiceImpl implements FoodsService {

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private FoodsRepository foodsRepository;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Boolean create(Foods food) {
        Example<Foods> foodsExample = Example.of(food);
        if (foodsRepository.exists(foodsExample)) {
            return false;
        }
        foodsRepository.save(food);
        return true; 
    }

    @Override
    public List<Foods> readAll() {
        return foodsRepository.findAll();
    }

    @Override
    public Foods read(UUID id) {
        Foods food = null;
        try {
            food = foodsRepository.findById(id).get();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
        return food;
    }

    @Override
    public Boolean deleteMulti(List<Foods> foodsList) {
        final List<UUID> idList = new ArrayList<>();
        for(Foods animal : foodsList) {
            idList.add(animal.getId());
        }
        
        foodsRepository.deleteAllById(idList);
        return true;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id == null) {
            return false;
        }
        if (foodsRepository.existsById(id)) {
            foodsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAll() {
        foodsRepository.deleteAll();
        return true;
    }

    /*public Foods updateFoodsLeftById(Foods food) {
        /*foodsRepository (food);
        return foodsRepository.findById(food.getId()).get();
    }*/

    @Override
    public Integer updateFoodsLeftById(UUID id, Integer newLeft) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.getNamedNativeQuery("Foods.updateFoodsLeftById");
        Integer result = 0;
        q.setParameter("id", id);
        q.setParameter("newLeft", newLeft);
        result = q.executeUpdate();
        tx.commit();
        return result;
    }

    public List<AnimalsFoodsMenuAggregator> readExtendedAnimalMenu() {
        Query q = em.createNamedQuery("Foods.readExtendedAnimalMenu");

        return q.getResultList();
    }

    @Override
    public Integer updateConsumeForSpecificAnimalPerDay(String animalname, String foodName, Double newPerDay) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createNamedQuery("Foods.updateConsumeForSpecificAnimalPerDay");
        Integer result = 0;
        q.setParameter("value", newPerDay);
        q.setParameter("animalName", animalname);
        q.setParameter("foodName", foodName);
        result = q.executeUpdate();
        tx.commit();
        
        return result;
    }

    @Override
    public List<AnimalsFoodsMenuAggregator> readMenuforSpecificAnimalPerWeek(String animalName) {
        Query q = em.createNamedQuery("Foods.readMenuforSpecificAnimalPerWeek");
        q.setParameter("animalName", animalName);
        return q.getResultList();
    }

    @Override
    public List<AnimalsFoodsMenuAggregator> readMenuforAllAnimalsOnAllWeek() {
        Query q = em.createNamedQuery("Foods.readMenuforAllAnimalsOnAllWeek");
        return q.getResultList();
    }
}
