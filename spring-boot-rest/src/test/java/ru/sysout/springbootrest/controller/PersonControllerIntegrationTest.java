package ru.sysout.springbootrest.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ru.sysout.springbootrest.dao.PersonRepository;
import ru.sysout.springbootrest.exception.EntityNotFoundException;
import ru.sysout.springbootrest.model.Person;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class PersonControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PersonRepository repository;

	@After
	public void resetDb() {
		repository.deleteAll();
	}

	@Test
	public void whenCreatePerson_thenStatus201() {

		Person person = new Person("Michail");

		ResponseEntity<Person> response = restTemplate.postForEntity("/persons", person, Person.class);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertThat(response.getBody().getId(), notNullValue());
		assertThat(response.getBody().getName(), is("Michail"));
	}

	@Test
	public void givenPerson_whenGetPerson_thenStatus200() {

		long id = createTestPerson("Joe").getId();

		Person person = restTemplate.getForObject("/persons/{id}", Person.class, id);
		assertThat(person.getName(), is("Joe"));
	}

	@Test
	public void whenUpdatePerson_thenStatus200() {

		long id = createTestPerson("Nick").getId();
		Person person = new Person("Michail");
		HttpEntity<Person> entity = new HttpEntity<Person>(person);

		ResponseEntity<Person> response = restTemplate.exchange("/persons/{id}", HttpMethod.PUT, entity, Person.class,
				id);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().getId(), notNullValue());
		assertThat(response.getBody().getName(), is("Michail"));
	}

	@Test
	public void givenPerson_whenDeletePerson_thenStatus200() {

		createTestPerson("Nick");
		restTemplate.delete("/persons/{id}", 1);

	}

	@Test
	public void givenPersons_whenGetPersons_thenStatus200() {
		createTestPerson("Joe");
		createTestPerson("Jane");
		ResponseEntity<List<Person>> response = restTemplate.exchange("/persons", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Person>>() {
				});
		List<Person> persons = response.getBody();
		assertThat(persons, hasSize(2));
		assertThat(persons.get(1).getName(), is("Jane"));
	}

	private Person createTestPerson(String name) {
		Person emp = new Person(name);
		return repository.saveAndFlush(emp);
	}

}
