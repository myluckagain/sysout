package ru.sysout.zoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.zoo.RandomAnimalClient;
import ru.sysout.zoo.model.Animal;

@RestController
public class ZooController {
    @Autowired
    private RandomAnimalClient randomAnimalClient;

    @GetMapping("/animals/any")
    ResponseEntity<Animal> seeAnyAnimal(){
        return randomAnimalClient.random();
    }
}
