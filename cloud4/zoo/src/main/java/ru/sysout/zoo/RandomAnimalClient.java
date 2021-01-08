package ru.sysout.zoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sysout.zoo.model.Animal;

@Component
public class RandomAnimalClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RandomAnimalClient.class);

    private final RestTemplate restTemplate;

    private final CircuitBreakerFactory circuitBreakerFactory;


    public RandomAnimalClient(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }


    public ResponseEntity<Animal> random() {
        LOGGER.debug("Sending  request for animal {}");

        return circuitBreakerFactory.create("randomAnimal").run(
                () -> restTemplate.getForEntity("http://random-animal/random", Animal.class),
                throwable -> fallbackRandom());
    }

    public ResponseEntity<Animal> fallbackRandom() {

        return ResponseEntity.ok().body(new Animal("no animal"));
    }
}
