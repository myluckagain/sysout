package ru.sysout.liqdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.liqdemo.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
