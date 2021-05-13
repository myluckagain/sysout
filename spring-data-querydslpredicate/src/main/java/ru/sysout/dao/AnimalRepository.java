package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.sysout.model.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long>, QuerydslPredicateExecutor {

    //животные, относящиеся к категории с названием "home"
    List<Animal> findAllByCategoryName(String categoryName);
    //животные, относящиеся к категории с названием "home" или "bird", в названии животного должны быть буква "g"
    List<Animal> findAllByCategoryNameInAndNameContaining(String[] categoryNames, String str);
    //животные, у которых название заканчивается на букву "t"
    List<Animal> findAllByNameEndingWith(String str);
}
