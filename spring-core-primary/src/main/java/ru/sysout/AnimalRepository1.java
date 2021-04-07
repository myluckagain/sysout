package ru.sysout;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AnimalRepository1 implements IAnimalRepository {
    @Override
    public void save() {
        System.out.println("AnimalRepository 1 save");
    }
}
