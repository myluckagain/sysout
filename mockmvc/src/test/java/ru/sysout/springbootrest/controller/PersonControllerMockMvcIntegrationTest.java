package ru.sysout.springbootrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sysout.springbootrest.dao.PersonRepository;
import ru.sysout.springbootrest.exception.EntityNotFoundException;
import ru.sysout.springbootrest.model.Person;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerMockMvcIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {

        Person person = new Person("Michail");

        mockMvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Michail"));
    }

    @Test
    public void givenId_whenGetExistingPerson_thenStatus200andPersonReturned() throws Exception {

        long id = createTestPerson("Michail").getId();

        mockMvc.perform(
                get("/persons/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Michail"));
    }

     @Test
    public void givenId_whenGetNotExistingPerson_thenStatus404anExceptionThrown() throws Exception {

        mockMvc.perform(
                get("/persons/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));

    }


    @Test
    public void givePerson_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {

        long id = createTestPerson("Nick").getId();


        mockMvc.perform(
                put("/persons/{id}", id)
                        .content(objectMapper.writeValueAsString(new Person("Michail")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Michail"));

    }


    @Test
    public void givenPerson_whenDeletePerson_thenStatus200() throws Exception {

        Person person = createTestPerson("Nick");

        mockMvc.perform(
                delete("/persons/{id}", person.getId()))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(person)));
    }

    @Test
    public void givenPersons_whenGetPersons_thenStatus200() throws Exception {
        Person p1 = createTestPerson("Jane");
        Person p2 =createTestPerson( "Joe");


        mockMvc.perform(
                get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }

    private Person createTestPerson(String name) {
        Person person = new Person(name);
        return repository.save(person);
    }
}
