package ru.javalang.lookup;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("ru.javalang.lookup")
public class LookupTest {
    @Test
    public void givenAnnotationConfig_whenLookupMethodCalled_ThenNewPassengerCreated() {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(LookupTest.class);
        Car car = javaConfigContext.getBean(Car.class);
        assertEquals(car.drive("John"), "car with John");
        assertEquals(car.drive("Michel"), "car with Michel");
    }

    @Test
    public void givenXmlConfig_whenLookupMethodCalled_ThenNewPassengerCreated() {
        ApplicationContext xmlConfigContext = new ClassPathXmlApplicationContext("lookup.xml");
        Car car = xmlConfigContext.getBean(Car.class);
        assertEquals(car.drive("John"), "car with John");
        assertEquals(car.drive("Michel"), "car with Michel");
    }
}
