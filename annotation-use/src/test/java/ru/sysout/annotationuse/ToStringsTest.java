package ru.sysout.annotationuse;


import org.junit.Test;
import static org.junit.Assert.*;

public class ToStringsTest {
    @Test
    public void whenCallToStrings_thenWorks(){
        Cat cat=new Cat();
        cat.setName("Vasya");
        cat.setBreed("besporody");
        String result=ToStrings.toString(cat);
        assertEquals(2,  result.split(",").length);
    }
}
