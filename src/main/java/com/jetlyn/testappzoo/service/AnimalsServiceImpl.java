package com.jetlyn.testappzoo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.jetlyn.testappzoo.entity.Animals;
import com.jetlyn.testappzoo.repository.AnimalsRepository;

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
        return animalsRepository.getById(id);
    }

    /*@Override
    public boolean update(Users user, String name) {
        if (usersRepository.exists(name)) {
            user.setName(name);
            usersRepository.save(user);
            return true;
        }

        return false;
    }*/

    @Override
    public Boolean delete(UUID id) {
        if (animalsRepository.existsById(id)) {
            animalsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteMulti(List<UUID> idList) {
        animalsRepository.deleteAllById(idList);
        return true;
    }

    @Override
    public Boolean deleteAll() {
        animalsRepository.deleteAll();
        return true;
    };
}
