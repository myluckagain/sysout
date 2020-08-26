package ru.sysout.springdatajdbc.dao;

import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;

public interface CustomAnimalRepository {

    Animal getById(long id);

    Animal insert(Animal animal);

    Animal update(Animal animal);

    List<Animal> getAll();
}
