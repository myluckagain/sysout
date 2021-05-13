package ru.sysout;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.AnimalRepository;
import ru.sysout.model.Animal;
import ru.sysout.model.Category;
import ru.sysout.model.QAnimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AnimalRepositoryTest {
    public static long HOME_ID = -3;
    public static long WILD_ID = -2;
    public static long BIRD_ID = -1;
    private Category home = new Category(HOME_ID, "home");
    private Category wild = new Category(WILD_ID, "wild");
    private Category bird = new Category(BIRD_ID, "bird");

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AnimalRepository animalRepository;

    //животные, относящиеся к категории с названием "home"
    @Test
    @DisplayName("с использованием QueryDSL без QuerydslPredicateExecutor")
    void test0() {

        Predicate predicate = QAnimal.animal.category.name.eq("home");

        QAnimal animal = QAnimal.animal;
        JPAQuery<Animal> query = new JPAQuery<>(entityManager);

        List<Animal> animals = query.select(animal).from(animal).where(predicate).fetch();

        List<Animal> expectedList = Arrays.asList(
                new Animal(1, "cat", home),
                new Animal(2, "dog", home),
                new Animal(4, "goat", home),
                new Animal(5, "cow", home));
        Assertions.assertEquals(expectedList, animals);


    }

    //животные, относящиеся к категории с названием "home"
    @Test
    void test1() {
        QAnimal animal = QAnimal.animal;
        Predicate predicate = animal.category.name.eq("home");

        Iterable animals = animalRepository.findAll(predicate);

        List<Animal> expectedList = Arrays.asList(
                new Animal(1, "cat", home),
                new Animal(2, "dog", home),
                new Animal(4, "goat", home),
                new Animal(5, "cow", home));
        Assertions.assertEquals(expectedList, animals);

        //с помощью findAllByCategoryName
        animals = animalRepository.findAllByCategoryName("home");

        Assertions.assertEquals(expectedList, animals);

    }

    //животные, относящиеся к категории с названием "home" или "bird", в названии животного должны быть буква "g"
    @Test
    void test2() {
        QAnimal animal = QAnimal.animal;
        Predicate predicate = animal.category.name.in("home", "bird")
                .and(animal.name.contains("g"));
        Iterable animals = animalRepository.findAll(predicate);


        List<Animal> expectedList = Arrays.asList(
                new Animal(2, "dog", home),
                new Animal(3, "eagle", bird),
                new Animal(4, "goat", home)
        );
        Assertions.assertEquals(expectedList, animals);

        //с помощью findAllByCategoryNameInAndNameContaining
        Iterable<Animal> animals2 = animalRepository.findAllByCategoryNameInAndNameContaining(new String[]{"home", "bird"}, "g");

        Assertions.assertEquals(expectedList, animals2);

    }

    //животные, у которых название заканчивается на букву "t"
    @Test
    void test3() {
        QAnimal animal = QAnimal.animal;

        Iterable animals = animalRepository.findAll(animal.name.endsWith("t"));


        List<Animal> expectedList = Arrays.asList(
                new Animal(1, "cat", home),
                new Animal(4, "goat", home)
        );
        Assertions.assertEquals(expectedList, animals);

        //с помощью findAllByNameEndingWith
        animals = animalRepository.findAllByNameEndingWith("t");

        Assertions.assertEquals(expectedList, animals);
    }
}