package ru.sysout.springbootrest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sysout.springbootrest.dao.PersonRepository;
import ru.sysout.springbootrest.exception.EntityNotFoundException;
import ru.sysout.springbootrest.model.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public ResponseEntity<List<Person>> listAllPersons() {

		List<Person> persons = personRepository.findAll();
		return ResponseEntity.ok().body(persons);

	}

	@GetMapping(value = "/{personId}")
	public ResponseEntity<Person> getPerson(@PathVariable("personId") Long personId) throws EntityNotFoundException {

		Optional<Person> person = personRepository.findById(personId);
		if (!person.isPresent())
			throw new EntityNotFoundException("id-" + personId);
		return ResponseEntity.ok().body(person.get());

	}

	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
		Person p = personRepository.save(person);
		return ResponseEntity.status(201).body(p);
	}

	@PutMapping(value = "/{personId}")
	public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person,
			@PathVariable("personId") Long personId) throws EntityNotFoundException {
		Optional<Person> p = personRepository.findById(personId);
		if (!p.isPresent())
			throw new EntityNotFoundException("id-" + personId);
		Person oldPerson = p.get();
		oldPerson.setName(person.getName());
		return ResponseEntity.ok().body(personRepository.save(oldPerson));

	}

	@DeleteMapping(value = "/{personId}")
	public ResponseEntity<Person> deletePerson(@PathVariable("personId") Long personId) throws EntityNotFoundException {
		Optional<Person> p = personRepository.findById(personId);
		if (!p.isPresent())
			throw new EntityNotFoundException("id-" + personId);

		personRepository.deleteById(personId);
		return ResponseEntity.ok().body(p.get());
	}
}
