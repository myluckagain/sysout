package ru.sysout.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.model.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long>, CustomAnimalRepository {

    List<Animal> findAllByNameContaining(String str, Pageable pageable);

    Page<Animal> findAllByName(String name, Pageable pageable);

    @Query("select a from Animal a")
    Page<Animal> findAllAnimals(Pageable pageable);

    @Query(value = "select * from animal", nativeQuery = true)
    Page<Animal> findAllAnimalsNative(Pageable pageable);

    @Query(value = "select count(animal.id) as animalsCount, category.name as categoryName " +
            "from animal " +
            "join category on animal.category_id=category.id " +
            "group by category.name",
            nativeQuery = true)
    List<CountView> findAnimalsCountByCategoryNative(Pageable pageable);
}
