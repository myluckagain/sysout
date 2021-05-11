package ru.sysout.springdatajdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.sysout.springdatajdbc.dao.AnimalRepository;
import ru.sysout.springdatajdbc.model.Animal;

import java.util.Arrays;
import java.util.List;

@DataJdbcTest
public class AnimalRepositoryTest {
    @Autowired
    private AnimalRepository dao;


    @Test
    void whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Page<Animal> animals = dao.findAll(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat"), new Animal(2, "dog")),
                animals.getContent());
    }

    @Test
    void whenFindSecondPageWithTwoElements_thenOk() {

        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);
        Page<Animal> animals = dao.findAll(secondPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(3, "eagle"), new Animal(4, "goat")),
                animals.getContent());

    }

    @Test
    void givenSort_whenFindFirstPageWithTwoElements_thenOk() {
        Pageable firstPageWithTwoElementsSortedByNameDesc =
                PageRequest.of(0, 2, Sort.by("name").descending());

        Page<Animal> animals = dao.findAll(firstPageWithTwoElementsSortedByNameDesc);

        Assertions.assertEquals(
                Arrays.asList(new Animal(6, "horse"), new Animal(4, "goat")),
                animals.getContent());
    }

    @Test
    void givenSort_whenFindAll_thenOk() {
        Sort sort = Sort.by("name").ascending();

        Iterable<Animal> animals = dao.findAll(sort);

        Assertions.assertEquals(
                Arrays.asList(
                        new Animal(1, "cat"),
                        new Animal(5, "cow"),
                        new Animal(2, "dog"),
                        new Animal(3, "eagle"),
                        new Animal(4, "goat"),
                        new Animal(6, "horse")
                ),
                animals);
    }

    @Test
    void givenStr_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        List<Animal> animals = dao.findAllByNameContaining("a", firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat"), new Animal(3, "eagle")),
                animals);
    }

    @Test
    void givenJdbcTemplate_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        Page<Animal> animals = dao.getAnimals(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat"), new Animal(2, "dog")),
                animals.getContent());
    }

}
