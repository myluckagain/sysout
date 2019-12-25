package ru.sysout.passingbyvalue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassingByRefOrByValueTest {
    private PassingByRefOrByValue pass;

    @Before
    public void init() {
        this.pass = new PassingByRefOrByValue();
    }

    @Test
    public void testM() {
        Cat cat = new Cat("Petya");
        Cat oldCat=cat;
        this.pass.m(cat);
        assertEquals("Vasya", cat.getName());
        assertEquals(oldCat, cat);
    }

    @Test
    public void testM1() {
        Cat cat = new Cat("Petya");
        Cat oldCat=cat;
        this.pass.m1(cat);
        assertEquals("Vasya", cat.getName());
        assertEquals(oldCat, cat);
    }
}
