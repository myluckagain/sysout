package ru.sysout.springdatajdbc.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;
@JdbcTest
@Import(AnimalRepository.class)
class AnimalRepositoryTest {
    @Autowired
    private AnimalRepository dao;

    @Test
    void givenData_whenGetOne_thenReturnsAnimal() {
        Animal animal = dao.getById(1L);
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

    @Test
    void givenAnimal_whenUpdate_thenReturnsNumber() {
        int numberOfRows = dao.update("mouse", 1l);
        Assertions.assertEquals(1, numberOfRows);
    }
}
