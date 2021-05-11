package ru.sysout.springdatajdbc.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long>, CustomAnimalRepository {

    List<Animal> findAllByNameContaining(String str, Pageable pageable);

}
