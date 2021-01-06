package ru.sysout.zoo;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketClient {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TicketClient.class);

    private final RestTemplate restTemplate;


    public TicketClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;


    }

    @Bulkhead(name = "ticket")
    public ResponseEntity<String> ticket() {
        return restTemplate.getForEntity("http://ticket", String.class);
    }

}
