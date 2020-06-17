package ru.sysout.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sysout.jwt.security.AuthRequest;
import ru.sysout.jwt.security.AuthResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetUser_thenCorrect() {

        AuthResponse authResponse = getAuthHeaderForUser("user", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/user", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertTrue(response.getBody().equals("User"));

    }

    @Test
    public void whenGetAdmin_thenCorrect() {


        AuthResponse authResponse = getAuthHeaderForUser("admin", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/admin", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertTrue(response.getBody().equals("Admin"));

    }

    @Test
    public void givenUserCredentials_whenGetAdmin_thenCorrect() {


        AuthResponse authResponse = getAuthHeaderForUser("user", "password");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authResponse.getJwtToken());

        ResponseEntity<String> response = restTemplate.exchange("/admin", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);

    }

    private AuthResponse getAuthHeaderForUser(String name, String password) {

        AuthRequest authRequest = new AuthRequest();
        authRequest.setName(name);
        authRequest.setPassword(password);
        AuthResponse authResponse = restTemplate.postForObject("/authenticate", authRequest, AuthResponse.class);

        return authResponse;
    }

}

