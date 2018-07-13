package ru.javalang.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class HamcrestFirstTest {
    @Test
    public void givenString_whenEqual_thenCorrect() {
        String string = "Petya";
        assertThat(string, equalTo("Petya"));
    }
}
