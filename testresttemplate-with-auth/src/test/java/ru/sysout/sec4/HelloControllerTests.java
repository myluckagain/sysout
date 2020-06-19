package ru.sysout.sec4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void whenGetUser_thenCorrect() {
        String securedUrl = "/user";

        String cookie = getCookieForUser("user", "password", "/login");


        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        ResponseEntity<String> responseFromSecuredEndPoint = testRestTemplate.exchange(securedUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertEquals(responseFromSecuredEndPoint.getStatusCode(), HttpStatus.OK);
        assertTrue(responseFromSecuredEndPoint.getBody().equals("User"));
    }


    public String getCookieForUser(String username, String password, String loginUrl) {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", username);
        form.set("password", password);

        ResponseEntity<String> loginResponse = testRestTemplate.postForEntity(
                loginUrl,
                new HttpEntity<>(form, new HttpHeaders()),
                String.class);
        String cookie = loginResponse.getHeaders().get("Set-Cookie").get(0);

        return cookie;
    }


}