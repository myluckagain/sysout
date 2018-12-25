package ru.sysout.springintegration.service;

import org.springframework.stereotype.Component;

import ru.sysout.springintegration.model.Animal;

@Component("bService")
public class B {
    public Animal process(Animal animal){
        System.out.println("B is processing animal");
        return animal;
    }
}
