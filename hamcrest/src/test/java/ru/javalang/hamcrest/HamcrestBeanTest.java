package ru.javalang.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;

public class HamcrestBeanTest {
    @Test
    public void givenBean_whenCheckProperty_thenHas() {

        Animal animal = new Animal("gaf");
        Dog dog = new Dog("gaf");

        assertThat(animal, Matchers.<Animal> hasProperty("sound"));
        assertThat(dog, Matchers.<Animal> hasProperty("sound"));
    }

    @Test
    public void givenBean_whenCheckPropertyValue_thenEqual() {

        Animal animal = new Animal("gaf");

        assertThat(animal, Matchers.<Animal> hasProperty("sound", equalTo("gaf")));
    }

    @Test
    public void given2Beans_whenHavingSameValues_thenCorrect() {
        Animal animal1 = new Animal("gaf");
        Animal animal2 = new Animal("gaf");
        assertThat(animal1, samePropertyValuesAs(animal2));
    }

}
