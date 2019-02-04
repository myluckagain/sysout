package ru.sysout.annotuser;


import org.junit.Test;
import static org.junit.Assert.*;
import ru.sysout.annotuser.ToStrings;

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
