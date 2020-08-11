package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class AnimalCart {
    private Set<Animal> animals=new HashSet<>();

    public void add(Animal animal){
        animals.add(animal);
    }

    public void clear(){
        animals.clear();
    }
}
