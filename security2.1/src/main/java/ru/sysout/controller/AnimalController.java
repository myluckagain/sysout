package ru.sysout.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AnimalController {

    private final List<Animal> list = new ArrayList<>();

    {
        list.add(new Animal("cat"));
        list.add(new Animal("dog"));
    }

    @GetMapping("/animal")
    public List<Animal> getAnimals() {
        return list;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAnimal(@RequestBody Animal animal) {
        list.add(animal);
        return animal;
    }

    //запостить можно только Animal.name=user (если user постит) и Animal.name=admin (если admin)
    @PreAuthorize("#animal.name == authentication.name")
    @PostMapping("/special")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAdmin(@RequestBody Animal animal) {
        list.add(animal);
        return animal;
    }
}
