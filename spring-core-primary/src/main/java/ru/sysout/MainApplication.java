package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MainApplication {
    @Autowired
    private AnimalService animalService;
	@Autowired
    private ListAnimalService listAnimalService;

    @PostConstruct
    public void test() {
        animalService.save();
        listAnimalService.save();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

    }

}
