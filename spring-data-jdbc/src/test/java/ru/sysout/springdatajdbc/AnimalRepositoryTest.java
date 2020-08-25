package ru.sysout.springdatajdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import ru.sysout.springdatajdbc.dao.AnimalRepository;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;

@DataJdbcTest
@Import(AnimalRepository.class)
class AnimalRepositoryTest {
    @Autowired
    private AnimalRepository dao;

    @Test
    void givenData_whenGetOne_thenReturnsAnimal() {
        Animal animal = dao.getById(1l);
        Assertions.assertEquals("cat", animal.getName());
    }

    @Test
    void givenData_whenGetAll_thenCountIsTwo() {
        List<Animal> animals = dao.getAll();
        Assertions.assertEquals(2, animals.size());
    }

    @Test
    void givenAnimal_whenInsert_thenReturnsAnimal() {
        Animal animal = dao.insert(new Animal("mouse"));
        Assertions.assertEquals("mouse", animal.getName());
    }
}
