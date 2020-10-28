package ru.sysout.testcontainersdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sysout.testcontainersdemo.dao.AnimalRepository;
import ru.sysout.testcontainersdemo.model.Animal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(initializers = {IntegrationSqlScriptTest.Initializer.class})
@Testcontainers
public class IntegrationSqlScriptTest {

    @Autowired
    AnimalRepository animalRepository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("mydb")
            .withUsername("myuser")
            .withPassword("mypass")
            .withInitScript("db.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()

            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
    @Test
    @Transactional
    public void animalsCountShouldBeCorrect() {
        long count = animalRepository.count();
        assertEquals(3, count);

    }

    @Test
    @Transactional
    public void animalsShouldBeCorrect() {
        List<Animal> animals = animalRepository.findAll();
        String[] actualAnimals = {"animal1", "animal2", "animal3"};
        assertArrayEquals(actualAnimals, animals.stream().map(animal -> animal.getName()).toArray());
    }
}