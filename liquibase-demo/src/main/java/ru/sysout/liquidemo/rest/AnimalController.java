package ru.sysout.liquidemo.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.liquidemo.dao.AnimalRepository;
import ru.sysout.liquidemo.model.Animal;

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
    public Animal post(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }
}
