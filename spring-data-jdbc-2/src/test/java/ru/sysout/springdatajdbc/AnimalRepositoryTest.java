package ru.sysout.springdatajdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import ru.sysout.springdatajdbc.dao.AnimalRepository;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;
import java.util.Optional;

@DataJdbcTest
public class AnimalRepositoryTest {
    @Autowired
    private AnimalRepository dao;

    @Test
    void givenId_whenFindThenReturnsAnimal() {
        Optional<Animal> optionalAnimal = dao.findById(1l);
        Assertions.assertTrue(!optionalAnimal.isEmpty());
        optionalAnimal.get().setName("cat");
    }

    @Test
    void givenAnimal_whenSaveThenReturnsAnimal() {
        Animal newAnimal=new Animal("mouse");
        Animal animal = dao.save(newAnimal);
        Assertions.assertSame(animal, newAnimal);
        Assertions.assertNotNull(animal.getId());
    }

    @Test
    void givenId_whenUpdateThenReturnsAnimal() {
        Optional<Animal> optionalAnimal = dao.findById(1l);
        optionalAnimal.get().setName("cat1");
        Animal updatedAnimal=dao.save(optionalAnimal.get());
        Assertions.assertEquals("cat1", updatedAnimal.getName());
    }

    @Test
    void whenCount_thenCountIsTwo() {
        long count = dao.count();
        Assertions.assertEquals(2 , count);
    }

    @Test
    void givenName_whenFindBy_thenReturnsAnimal() {
        Animal animal = dao.findByName("cat");
        Assertions.assertEquals("cat", animal.getName());
    }

    @Test
    void givenName_whenFindFirstBy_thenReturnsAnimal() {
        Animal animal = dao.findFirstByName("cat");
        Assertions.assertEquals("cat", animal.getName());
    }

    @Test
    void givenName_whenFindByNameNotContaining_thenCorrect() {
        List<Animal> animals = dao.findByNameNotContaining("cat");
        Assertions.assertEquals(1, animals.size());
    }

}
