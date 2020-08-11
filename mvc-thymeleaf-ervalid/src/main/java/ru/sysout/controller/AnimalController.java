package ru.sysout.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sysout.model.Animal;
import ru.sysout.model.AnimalCart;

import javax.validation.Valid;
import java.util.Set;

@AllArgsConstructor
@Controller
public class AnimalController {
    private final AnimalCart animalCart;

    @GetMapping("/")
    public String get(Model model) {
        Set<Animal> animals = animalCart.getAnimals();

        model.addAttribute("animals", animals);
        model.addAttribute("animal", new Animal());
        return "index";
    }

    @PostMapping("/")
    public String add(@Valid Animal animal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("animals", animalCart.getAnimals());
            return "index";
        }
        animalCart.add(animal);
        return "redirect:/";
    }
}
