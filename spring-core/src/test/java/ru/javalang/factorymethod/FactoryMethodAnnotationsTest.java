package ru.javalang.factorymethod;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryMethodAnnotationsTest {
    private AnnotationConfigApplicationContext javaConfigApplicationContext;

    @Before
    public void init() {
        javaConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
    }

    @Test
    public void whenStaticMethodCalled_ThenObjectExist() {
        assertTrue(javaConfigApplicationContext.getBean("dogstatic") instanceof Dog);
        assertTrue(javaConfigApplicationContext.getBean("duckstatic") instanceof Duck);
    }

    @Test
    public void givenSingletonConfig_WhenStaticMethodCalled_ThenObjectsEqual() {
        Object dog1 = javaConfigApplicationContext.getBean("dogstatic");
        Object dog2 = javaConfigApplicationContext.getBean("dogstatic");

        assertEquals(dog1, dog2);
    }

    @Test
    public void givenPrototypeConfig_WhenStaticMethodCalled_ThenObjectsDifferent() {

        Object duck1 = javaConfigApplicationContext.getBean("duckstatic");
        Object duck2 = javaConfigApplicationContext.getBean("duckstatic");
        assertNotEquals(duck1, duck2);
    }

    @Test
    public void whenInstanceMethodCalled_ThenObjectExist() {
        assertTrue(javaConfigApplicationContext.getBean("doginstance") instanceof Dog);
        assertTrue(javaConfigApplicationContext.getBean("duckinstance") instanceof Duck);

    }

    @Test
    public void givenSingletonConfig_WhenInstanceMethodCalled_ThenObjectsEqual() {
        Object dog1 = javaConfigApplicationContext.getBean("doginstance");
        Object dog2 = javaConfigApplicationContext.getBean("doginstance");
        assertEquals(dog1, dog2);
    }

    @Test
    public void givenPrototypeConfig_WhenInstanceMethodCalled_ThenObjectsDifferent() {

        Object duck1 = javaConfigApplicationContext.getBean("duckinstance");
        Object duck2 = javaConfigApplicationContext.getBean("duckinstance");
        assertNotEquals(duck1, duck2);
    }
}
