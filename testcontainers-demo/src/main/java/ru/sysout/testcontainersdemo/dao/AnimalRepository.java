package ru.sysout.testcontainersdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.testcontainersdemo.model.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
