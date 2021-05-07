package ru.sysout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainTests {
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void whenLogger_thenLog() {
        animalRepository.save();
    }

}
