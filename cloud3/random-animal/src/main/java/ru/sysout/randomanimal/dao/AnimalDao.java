package ru.sysout.randomanimal.dao;

import org.springframework.stereotype.Component;
import ru.sysout.randomanimal.model.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class AnimalDao {
    private List<Animal> list = Arrays.asList(new Animal("cat"), new Animal("dog"), new Animal("fox"));

    public Animal random(){
        Random rand = new Random();
        return  list.get(rand.nextInt(list.size()));
    }
}
