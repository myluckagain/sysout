package ru.javalang.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestCoreStringNumberTest

{

    @Test
    public void givenString_whenConditions_thenCorrect() {
        String string = "Petya";
        assertThat(string, anyOf(containsString("Pet"), endsWith("tya")));
    }

    @Test
    public void givenNumber_whenConditions_thenCorrect() {
        Integer intVal = 7;
        assertThat(intVal, allOf(greaterThan(5), lessThanOrEqualTo(7), not(equalTo(6))));
    }

    @Test
    public void whenNotNull_thenCorrect() {
        String str = new String();
        assertThat(str, notNullValue());
    }

    @Test
    public void whenSameObject_thenCorrect() {

        Object object = new String();

        assertThat(object, sameInstance(object));
    }

    @Test
    public void givenDouble_whenCompare_thenCorrect() {
        Double doubleVal = 7.70001;
        assertThat(doubleVal, closeTo(7.7, 0.005));
    }

    @Test
    public void given2Strings_whenEqual_thenCorrect() {
        String a = "Petya";
        String b = "petya";
        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void given2Strings_whenEqualIgnWS_thenCorrect() {

        String a = "  Hi   Petya ";
        assertThat(a, equalToIgnoringWhiteSpace("Hi Petya"));
    }

    
    @Test
    public void givenNumber_whenOurConditions_thenCorrect() {
        Integer intVal = 7;
        assertThat(intVal, OurNumberMatcher.isOurNumber());
    }
}
