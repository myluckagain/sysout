package ru.sysout.springintegration.service;

import org.springframework.stereotype.Service;
import ru.sysout.springintegration.model.Animal;

@Service("bService")
public class B {

    public Animal process(Animal animal) {
        System.out.println("B is processing animal: " + animal.getName());
        return animal;
    }
}
