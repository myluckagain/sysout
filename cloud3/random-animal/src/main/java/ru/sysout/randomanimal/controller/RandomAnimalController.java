package ru.sysout.randomanimal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.randomanimal.dao.AnimalDao;
import ru.sysout.randomanimal.model.Animal;

@RestController
public class RandomAnimalController {

    private final AnimalDao animalDao;


    public RandomAnimalController(AnimalDao animalDao){
        this.animalDao=animalDao;
     }

    @GetMapping("/random")
    public Animal randomAnimal(){
        Animal animal=animalDao.random();
        System.out.println(animal);
        return animal;
    }
}
