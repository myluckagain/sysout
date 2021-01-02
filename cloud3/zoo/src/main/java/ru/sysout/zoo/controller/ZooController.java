package ru.sysout.zoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.zoo.RandomAnimalClient;
import ru.sysout.zoo.model.Animal;

@RestController
@RefreshScope
public class ZooController {
    @Autowired
    private RandomAnimalClient randomAnimalClient;

    @Value("${greeting}")
    String greeting;

    @GetMapping("/animals/any")
    Animal seeAnyAnimal() {
        Animal animal = randomAnimalClient.random().getBody();
        animal.setName(this.greeting + ", " + animal.getName());
        return animal;
    }
}
