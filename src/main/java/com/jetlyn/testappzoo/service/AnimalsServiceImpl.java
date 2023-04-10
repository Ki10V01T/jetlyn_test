package com.jetlyn.testappzoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jetlyn.testappzoo.entity.Animals;
import com.jetlyn.testappzoo.repository.AnimalsRepository;

@Service
public class AnimalsServiceImpl implements AnimalsService {
    
    @Autowired
    private AnimalsRepository animalsRepository;

    @Override
    public Boolean create(Animals animal) {
        Example<Animals> animalExample = Example.of(animal);
        if (animalsRepository.exists(animalExample)) {
            return false;
        }
        animalsRepository.save(animal);
        return true; 
    }

    @Override
    public List<Animals> readAll() {
        return animalsRepository.findAll();
    }
    
    @Override
    public Animals read(UUID id) {
        Animals a = null;
        try {
            a = animalsRepository.findById(id).get();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
        
        return a;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id == null) {
            return false;
        }
        if (animalsRepository.existsById(id)) {
            animalsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteMulti(List<Animals> animalsList) {
        final List<UUID> idList = new ArrayList<>();
        for(Animals animal : animalsList) {
            idList.add(animal.getId());
        }
        try {
            animalsRepository.deleteAllById(idList);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean deleteAll() {
        animalsRepository.deleteAll();
        return true;
    };
}
