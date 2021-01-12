package ru.sysout.listtoarray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListToArrayTest {

    private List<String> list = new ArrayList<>();


    @Test
    public void m1() {
        List<String> list = new ArrayList<>();
        list.add("Petya");
        list.add("Vasya");
        //Java 8
        String[] stringArray = list.toArray(new String[0]);

        assertThat(stringArray[0], is("Petya"));
        assertThat(stringArray[1], is("Vasya"));
    }


    @Test
    public void m2() {

        list.add("Petya");
        list.add("Vasya");
        //Java 11
        String[] stringArray = list.toArray(String[]::new);

        assertThat(stringArray[0], is("Petya"));
        assertThat(stringArray[1], is("Vasya"));
    }

    @Test
    public void m3() {

        list.add("Petya");
        list.add("Vasya");
        //Java 8
        String[] stringArray = list.stream().toArray(String[]::new);

        assertThat(stringArray[0], is("Petya"));
        assertThat(stringArray[1], is("Vasya"));
    }


    private static <T> String[] listToArray(List<T> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = list.get(i).toString();
        return array;
    }

    @Test
    public void m4() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Petya"));
        list.add(new Person("Vasya"));


        String[] stringArray = listToArray(list);

        assertThat(stringArray[0], is("Petya"));
        assertThat(stringArray[1], is("Vasya"));
    }

    @Test
    public void m5() {

        String[] stringArray = new String[]{"Petya", "Vasya"};
        List<String> list = Arrays.asList(stringArray);

        assertThat(stringArray[0], is("Petya"));
        assertThat(stringArray[1], is("Vasya"));
    }


    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
