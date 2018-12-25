package ru.sysout.springintegration.service;

import org.springframework.stereotype.Component;

import ru.sysout.springintegration.model.Animal;

@Component("cService")
public class C {
    public Animal process(Animal animal) {
        System.out.println("C is processing animal");
        return animal;
    }
}
