package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.sysout.dao.AnimalRepository;
import ru.sysout.dao.CountView;
import ru.sysout.model.Animal;
import ru.sysout.model.Category;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class AnimalRepositoryTest {
    public static long HOME_ID=-3;
    public static long WILD_ID=-2;
    public static long BIRD_ID=-1;
    private Category home=new Category(HOME_ID,"home");
    private Category wild=new Category(WILD_ID,"wild");
    private Category bird=new Category(BIRD_ID,"bird");

    @Autowired
    private AnimalRepository dao;


    @Test
    void whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Page<Animal> animals = dao.findAll(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat", home), new Animal(2, "dog", home)),
                animals.getContent());
    }

    @Test
    void whenFindSecondPageWithTwoElements_thenOk() {

        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);
        Page<Animal> animals = dao.findAll(secondPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(3, "eagle", bird), new Animal(4, "goat", home)),
                animals.getContent());

    }

    @Test
    void givenSort_whenFindFirstPageWithTwoElements_thenOk() {
        Pageable firstPageWithTwoElementsSortedByNameDesc =
                PageRequest.of(0, 2, Sort.by("name").descending());

        Page<Animal> animals = dao.findAll(firstPageWithTwoElementsSortedByNameDesc);

        Assertions.assertEquals(
                Arrays.asList(new Animal(6, "horse", wild), new Animal(4, "goat", home)),
                animals.getContent());
    }

    @Test
    void givenSort_whenFindAll_thenOk() {
        Sort sort = Sort.by("name").ascending();

        Iterable<Animal> animals = dao.findAll(sort);

        Assertions.assertEquals(
                Arrays.asList(
                        new Animal(1, "cat", home),
                        new Animal(5, "cow", home),
                        new Animal(2, "dog", home),
                        new Animal(3, "eagle", bird),
                        new Animal(4, "goat", home),
                        new Animal(6, "horse", wild)
                ),
                animals);
    }

    @Test
    void givenStr_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        List<Animal> animals = dao.findAllByNameContaining("a", firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat", home), new Animal(3, "eagle", bird)),
                animals);
    }

    @Test
    void givenName_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        Page<Animal> animals = dao.findAllByName("cat", firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat",  home)),
                animals.getContent());
    }

    @Test
    void givenQuery_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        Page<Animal> animals = dao.findAllAnimals(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat", home), new Animal(2, "dog", home)),
                animals.getContent());
    }

    @Test
    void givenNativeQuery_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        Page<Animal> animals = dao.findAllAnimalsNative(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat", home), new Animal(2, "dog", home)),
                animals.getContent());
    }

    @Test
    void givenNativeQuery_whenFindGroups_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2,  Sort.by("animalsCount").and(Sort.by("categoryName")));

        List<CountView> animals = dao.findAnimalsCountByCategoryNative(firstPageWithTwoElements);

        Assertions.assertEquals(2, animals.size());

        CountView bird=animals.get(0);

        Assertions.assertEquals(1, bird.getAnimalsCount());
        Assertions.assertEquals("bird", bird.getCategoryName());

        CountView wild=animals.get(1);

        Assertions.assertEquals(1, wild.getAnimalsCount());
        Assertions.assertEquals("wild", wild.getCategoryName());
    }


    @Test
    void givenEntityManager_whenFindFirstPageWithTwoElements_thenOk() {

        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Page<Animal> animals = dao.getAnimals(firstPageWithTwoElements);

        Assertions.assertEquals(
                Arrays.asList(new Animal(1, "cat", home), new Animal(2, "dog", home)),
                animals.getContent());
    }
}
