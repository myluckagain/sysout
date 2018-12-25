package ru.sysout.springintegration.service;

import org.springframework.stereotype.Component;
import ru.sysout.springintegration.model.Animal;

@Component("aService")
public class A {

    public Animal process(Animal animal) {
        System.out.println("A is processing animal");
        return animal;
    }
}
