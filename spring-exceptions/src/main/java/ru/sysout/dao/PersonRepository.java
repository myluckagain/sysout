package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
