package ru.javalang.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("ru.javalang.ioc")
@Configuration
public class Config {
    @Bean
    public Animal createAnimal() {
        return new Animal();
    }
}
