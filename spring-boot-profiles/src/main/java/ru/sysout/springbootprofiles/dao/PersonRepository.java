package ru.sysout.springbootprofiles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sysout.springbootprofiles.model.Person;



@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
