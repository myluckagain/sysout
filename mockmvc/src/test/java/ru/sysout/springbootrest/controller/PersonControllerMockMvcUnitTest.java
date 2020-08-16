package ru.sysout.springbootrest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sysout.springbootrest.dao.PersonRepository;
import ru.sysout.springbootrest.exception.EntityNotFoundException;
import ru.sysout.springbootrest.model.Person;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PersonControllerMockMvcUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PersonRepository repository;


    @Test
    public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {

        Person person = new Person(1l, "Michail");
        Mockito.when(repository.save(Mockito.any())).thenReturn(person);

        mockMvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(person)));
    }

    @Test
    public void givenId_whenGetExistingPerson_thenStatus200andPersonReturned() throws Exception {

        Person person = new Person(1l, "Michail");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(person));

        mockMvc.perform(
                get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Michail"));
    }


    @Test
    public void givenId_whenGetNotExistingPerson_thenStatus404anExceptionThrown() throws Exception {


        Mockito.when(repository.findById(Mockito.any())).
                thenReturn(Optional.empty());

        mockMvc.perform(
                get("/persons/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));

    }


    @Test
    public void givePerson_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {

        Person person = new Person(1l, "Michail");
        Mockito.when(repository.save(Mockito.any())).thenReturn(person);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(person));

        mockMvc.perform(
                put("/persons/1")
                        .content(objectMapper.writeValueAsString(new Person("Michail")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Michail"));

    }


    @Test
    public void givenPerson_whenDeletePerson_thenStatus200() throws Exception {

        Person person = new Person(1l, "Michail");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(person));

        mockMvc.perform(
                delete("/persons/1"))
                .andExpect(status().isOk());


    }

    @Test
    public void givenPersons_whenGetPersons_thenStatus200() throws Exception {
        Person p1 = new Person(1l, "Jane");
        Person p2 = new Person(1l, "Joe");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(
                get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }
}
