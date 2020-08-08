package ru.sysout.dockerdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.dockerdemo.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
