package ru.sysout.springbootrest.controller;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import ru.sysout.springbootrest.dao.PersonRepository;
import ru.sysout.springbootrest.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class PersonControllerIntegrationTest {
	@LocalServerPort
	private int port;
	@Autowired
	private PersonRepository repository;

	@Before
	public void setup() {

		RestAssured.port = port;
	}

	@After
	public void resetDb() {
		repository.deleteAll();
		repository.flush();
	}

	@Test
	public void whenCreatePerson_thenStatus201() {

		Person person = new Person("Michail");
		
		given().log().body()
		.contentType("application/json").body(person)
		
		.when().post("/persons")
		
		.then().log().body()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void givenPerson_whenGetPerson_thenStatus200() {

		long id = createTestPerson("Joe").getId();

		given().pathParam("id", id)
		
		.when().get("/persons/{id}")
		
		.then().log().body().statusCode(HttpStatus.OK.value())
				.and().body("name", equalTo("Joe"));

	}

	@Test
	public void givenNoPerson_whenGetPerson_thenStatus500() {

		given().pathParam("id", 1).when().get("/persons/{id}").then().log().body()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

	}

	@Test
	public void whenUpdatePerson_thenStatus200() {

		long id = createTestPerson("Nick").getId();
		Person person = new Person("Michail");

		given().pathParam("id", id).log().body().contentType("application/json").body(person).when()
				.put("/persons/{id}").then().log().body().statusCode(HttpStatus.OK.value()).and()
				.body("name", equalTo("Michail"));

	}

	@Test
	public void givenPerson_whenDeletePerson_thenStatus200() {

		long id = createTestPerson("Nick").getId();
		given().pathParam("id", id).log().body().contentType("application/json").when().delete("/persons/{id}").then()
				.log().body().statusCode(HttpStatus.OK.value()).and().body("name", equalTo("Nick"));

	}

	@Test
	public void givenPersons_whenGetPersons_thenStatus200() {
		createTestPerson("Joe");
		createTestPerson("Jane");

		when().get("/persons")
		.then().log().body()
		.statusCode(HttpStatus.OK.value())
		.and().body("get(0).name", equalTo("Joe"))
		.and().body("get(1).name", equalTo("Jane"))
		;
	}

	private Person createTestPerson(String name) {
		Person emp = new Person(name);
		return repository.saveAndFlush(emp);
	}

}
