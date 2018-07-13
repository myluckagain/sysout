package ru.javalang.factorymethod;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryMethodTest {
    private ClassPathXmlApplicationContext xmlConfigContext;

    @Before
    public void init() {
        xmlConfigContext = new ClassPathXmlApplicationContext("factorymethod.xml");
    }

    @Test
    public void whenStaticMethodCalled_ThenObjectExist() {
        assertTrue(xmlConfigContext.getBean("dogstatic") instanceof Dog);
        assertTrue(xmlConfigContext.getBean("duckstatic") instanceof Duck);
    }

    @Test
    public void givenSingletonConfig_WhenStaticMethodCalled_ThenObjectsEqual() {
        Object dog1 = xmlConfigContext.getBean("dogstatic");
        Object dog2 = xmlConfigContext.getBean("dogstatic");
        
        assertEquals(dog1, dog2);
    }

    @Test
    public void givenPrototypeConfig_WhenStaticMethodCalled_ThenObjectsDifferent() {

        Object duck1 = xmlConfigContext.getBean("duckstatic");
        Object duck2 = xmlConfigContext.getBean("duckstatic");
        assertNotEquals(duck1, duck2);
    }

    @Test
    public void whenInstanceMethodCalled_ThenObjectExist() {
        assertTrue(xmlConfigContext.getBean("doginstance") instanceof Dog);
        assertTrue(xmlConfigContext.getBean("duckinstance") instanceof Duck);

    }

    @Test
    public void givenSingletonConfig_WhenInstanceMethodCalled_ThenObjectsEqual() {
        Object dog1 = xmlConfigContext.getBean("doginstance");
        Object dog2 = xmlConfigContext.getBean("doginstance");
        assertEquals(dog1, dog2);
    }

    @Test
    public void givenPrototypeConfig_WhenInstanceMethodCalled_ThenObjectsDifferent() {

        Object duck1 = xmlConfigContext.getBean("duckinstance");
        Object duck2 = xmlConfigContext.getBean("duckinstance");
        assertNotEquals(duck1, duck2);
    }
}
