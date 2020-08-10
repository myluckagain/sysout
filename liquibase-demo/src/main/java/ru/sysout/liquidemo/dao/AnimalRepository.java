package ru.sysout.liquidemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.liquidemo.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
