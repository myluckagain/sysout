package ru.javalang.ioc;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerTest {

    @Test
    public void givenAnnotationConfig_whenContextCreated_ThenBeansGot() {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);
        Animal animal = javaConfigContext.getBean(Animal.class);
        Man man = javaConfigContext.getBean(Man.class);

        assertNotNull(animal);
        assertNotNull(man);

    }
}
