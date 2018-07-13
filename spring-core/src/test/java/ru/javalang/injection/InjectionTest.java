package ru.javalang.injection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class InjectionTest {

    @Test
    public void givenAnnotationConfig_whenConstructorInjected_ThenEngineExist() {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);
        CarWithConstructor carWithConstructor = javaConfigContext.getBean(CarWithConstructor.class);
        assertNotNull(carWithConstructor.getEngine());

    }

    @Test
    public void givenAnnotationConfig_whenSetterInjected_ThenEngineExist() {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);
        CarWithSetter carWithSetter = javaConfigContext.getBean(CarWithSetter.class);
        assertNotNull(carWithSetter.getEngine());

    }

    @Test
    public void givenXmlConfig_whenConstructorInjected_ThenEngineExist() {
        ApplicationContext xmlConfigContext = new ClassPathXmlApplicationContext("injection.xml");
        CarWithConstructor carWithConstructor = xmlConfigContext.getBean(CarWithConstructor.class);
        assertNotNull(carWithConstructor.getEngine());

    }

    @Test
    public void givenXmlConfig_whenSetterInjected_ThenEngineExist() {
        ApplicationContext xmlConfigContext = new ClassPathXmlApplicationContext("injection.xml");
        CarWithSetter carWithSetter = xmlConfigContext.getBean(CarWithSetter.class);
        assertNotNull(carWithSetter.getEngine());

    }

}
