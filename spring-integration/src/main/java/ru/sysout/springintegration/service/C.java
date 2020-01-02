package ru.sysout.springintegration.service;

import org.springframework.stereotype.Service;
import ru.sysout.springintegration.model.Animal;

@Service("cService")
public class C {

    public Animal process(Animal animal) {
        System.out.println("C is processing animal: " + animal.getName());
        return animal;
    }
}
