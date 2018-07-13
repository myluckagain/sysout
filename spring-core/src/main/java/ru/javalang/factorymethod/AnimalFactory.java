package ru.javalang.factorymethod;

public class AnimalFactory {
 
    public Animal getAnimal(String animalType) {
        if ("Dog".equalsIgnoreCase(animalType)) {
            return new Dog();
        } else if ("Duck".equalsIgnoreCase(animalType)) {
            return new Duck();
        }
        return null;
    }
}
