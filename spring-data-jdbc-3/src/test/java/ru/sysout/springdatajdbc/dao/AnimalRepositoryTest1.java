package ru.sysout.springdatajdbc.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.List;

@DataJdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AnimalRepositoryTest1 {
    @Autowired
    private AnimalRepository dao;

    @Test
    void givenData_whenGetAll_thenCountIsTwo() {
        List<Animal> animals = dao.getAll();
        Assertions.assertEquals(2, animals.size());
    }

    // пользовательский метод
    @Test
    void givenAnimal_whenInsert_thenReturnsAnimal() {
        Animal animal = dao.insert(new Animal("mouse"));
        Assertions.assertEquals("mouse", animal.getName());
    }
    //Query method
    @Test
    void givenName_whenFindBy_thenReturnsAnimal() {
        Animal animal = dao.findByName("cat");
        Assertions.assertEquals("cat", animal.getName());
    }
}
