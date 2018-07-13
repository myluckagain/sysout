package ru.javalang.factorymethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean(name = "dogstatic")
    public Animal createStatDog() {
        return Animal.getAnimal("dog");
    }

    @Bean(name = "duckstatic")
    @Scope("prototype")
    public Animal createStatDuck() {
        return Animal.getAnimal("duck");
    }

    @Bean(name = "doginstance")
    public Animal createInstDog() {
        return new AnimalFactory().getAnimal("dog");
    }

    @Bean(name = "duckinstance")
    @Scope("prototype")
    public Animal createInstDuck() {
        return new AnimalFactory().getAnimal("duck");
    }
}
