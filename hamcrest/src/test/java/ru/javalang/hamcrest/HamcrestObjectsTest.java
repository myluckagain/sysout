package ru.javalang.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestObjectsTest {

    @Test
    public void givenDog_whenToString_thenReturnsSound() {

        Dog dog = new Dog("gaf");
        assertThat(dog, hasToString("gaf"));
    }

    @Test
    public void givenSubclass_whenTypeCompatable_thenTrue() {

        assertThat(Dog.class, typeCompatibleWith(Animal.class));
        assertThat(Integer.class, typeCompatibleWith(Number.class));

    }

    @Test
    public void givenSubclass_whenChecked_thenTrue() {

        Dog dog = new Dog("gaf");
        assertThat(dog, instanceOf(Animal.class));
        assertThat(dog, isA(Animal.class));
    }

}
