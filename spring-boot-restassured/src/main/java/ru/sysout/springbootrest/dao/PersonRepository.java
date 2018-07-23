package ru.sysout.springbootrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sysout.springbootrest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
