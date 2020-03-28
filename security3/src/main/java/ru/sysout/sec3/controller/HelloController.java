package ru.sysout.sec3.controller;

import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.sec3.security.SecurityConfig;

@RestController
@Import(SecurityConfig.class)
public class HelloController {

    @GetMapping("/")
    public String hello(Authentication a) {
        return "Hello";
    }

    @GetMapping("/user")
    public String user(Authentication a) {
        return "User";
    }


    @GetMapping("/admin")
    public String basic(Authentication a) {
        return "Admin";
    }


}
