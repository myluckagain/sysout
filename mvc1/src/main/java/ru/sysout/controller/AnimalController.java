package ru.sysout.mvc1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;
import ru.sysout.mvc1.dao.AnimalRepository;
import ru.sysout.mvc1.model.Animal;
import ru.sysout.mvc1.model.ProductCart;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final ProductCart productCart;

    @GetMapping("/")
    public String get(Model model) {
        Set<Animal> animals = productCart.getProducts();
        model.addAttribute("animals", animals);
        model.addAttribute("animal", new Animal());
        return "index";
    }
    @PostMapping("/")
    public String add( Animal animal) {
        productCart.add(animal);
        return  "redirect:/";
    }
}
