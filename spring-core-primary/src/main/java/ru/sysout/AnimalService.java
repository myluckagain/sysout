package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    private IAnimalRepository animalRepository;

    @Autowired
    public AnimalService(@Qualifier("animalRepository2") IAnimalRepository animalRepository){
        this.animalRepository=animalRepository;
    }

    public void save(){
        animalRepository.save();
    }
}
