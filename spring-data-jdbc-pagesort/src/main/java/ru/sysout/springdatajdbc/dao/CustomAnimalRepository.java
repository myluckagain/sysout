package ru.sysout.springdatajdbc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sysout.springdatajdbc.model.Animal;

public interface CustomAnimalRepository {
    Page<Animal> getAnimals(Pageable pageable);
}
