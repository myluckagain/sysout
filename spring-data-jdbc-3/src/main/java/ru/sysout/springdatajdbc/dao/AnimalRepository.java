package ru.sysout.springdatajdbc.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long>, CustomAnimalRepository {

    @Query("select count(*) from animal")
    int animalCount();

    Animal findByName(String name);

    Animal findFirstByName(String name);

    List<Animal> findByNameNotContaining(String str);
}
