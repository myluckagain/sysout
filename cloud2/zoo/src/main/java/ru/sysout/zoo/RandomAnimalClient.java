package ru.sysout.zoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.sysout.zoo.model.Animal;

@Component
public class RandomAnimalClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RandomAnimalClient.class);

    private final RestTemplate simpleRestTemplate;
    private final RestTemplate loadBalancedTemplate;
    private final DiscoveryClient discoveryClient;

    RandomAnimalClient(@Qualifier("simpleTemplate") RestTemplate simpleRestTemplate,
                       @LoadBalanced RestTemplate loadBalancedTemplate,
                       DiscoveryClient discoveryClient) {
        this.simpleRestTemplate = simpleRestTemplate;
        this.loadBalancedTemplate = loadBalancedTemplate;
        this.discoveryClient = discoveryClient;
    }


    public ResponseEntity<Animal> random1() {
        LOGGER.debug("Sending  request for animal {}");
        return loadBalancedTemplate.getForEntity("http://proxy/random-animal/random",
                Animal.class);
    }

    public ResponseEntity<Animal> random() {
        System.out.println(discoveryClient.getInstances("proxy"));
        ServiceInstance instance = discoveryClient.getInstances("proxy")
                .stream().findAny()
                .orElseThrow(() -> new IllegalStateException("proxy service unavailable"));

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(instance.getUri().toString() + "/random-animal/random");
        return simpleRestTemplate.getForEntity(uriComponentsBuilder.toUriString(), Animal.class);
    }
}
