package ru.sysout.zoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.sysout.zoo.model.Animal;

@Component
public class RandomAnimalClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RandomAnimalClient.class);

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    RandomAnimalClient(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }


    public ResponseEntity<Animal> random() {
        LOGGER.debug("Sending  request for animal {}");
        return restTemplate.getForEntity("http://random-animal/random",
                Animal.class);
    }

    public ResponseEntity<Animal> random1() {

        ServiceInstance instance = discoveryClient.getInstances("random-animal")
                .stream().findAny()
                .orElseThrow(() -> new IllegalStateException("Random-animal service unavailable"));

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(instance.getUri().toString() + "/random");
        return restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Animal.class);
    }
}
