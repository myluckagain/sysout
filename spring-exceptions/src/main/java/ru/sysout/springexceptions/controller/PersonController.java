package ru.sysout.springexceptions.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sysout.springexceptions.dao.PersonRepository;
import ru.sysout.springexceptions.exception.EntityNotFoundException;
import ru.sysout.springexceptions.model.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> listAllPersons() throws  EntityNotFoundException {
      //  personRepository.save(new Person("Kate"));
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @PostMapping
    public Person createPerson(@RequestBody @Valid Person person) {
        return personRepository.save(person);
    }
    
    @GetMapping(value = "/{personId}")
    public Person getPerson(@PathVariable("personId") Long personId) throws EntityNotFoundException {
        return personRepository.getOne(personId);
    }

}
