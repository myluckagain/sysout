package ru.sysout.springintegration.service;

import org.springframework.stereotype.Service;
import ru.sysout.springintegration.model.Animal;

@Service("aService")
public class A {

    public Animal process(Animal animal) {
        System.out.println("A is processing animal: " + animal.getName());
        return animal;
    }
}
