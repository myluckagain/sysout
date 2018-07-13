package ru.sysout.springexceptions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sysout.springexceptions.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
