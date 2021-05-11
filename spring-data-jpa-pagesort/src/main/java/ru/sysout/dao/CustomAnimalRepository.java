package ru.sysout.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sysout.model.Animal;

public interface CustomAnimalRepository {
    Page<Animal> getAnimals(Pageable pageable);
}
