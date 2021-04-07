package ru.sysout;

import org.springframework.stereotype.Repository;

@Repository
public class AnimalRepository2 implements IAnimalRepository {
    @Override
    public void save() {
        System.out.println("AnimalRepository 2 save");
    }
}