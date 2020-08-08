package ru.sysout.dockerdemo.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sysout.dockerdemo.dao.AnimalRepository;
import ru.sysout.dockerdemo.model.Animal;

import java.util.List;

@RestController
@AllArgsConstructor
public class AnimalController {
    private final AnimalRepository animalRepository;

    @GetMapping("/api/animals")
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @PostMapping("/api/animals")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal post(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }
}
