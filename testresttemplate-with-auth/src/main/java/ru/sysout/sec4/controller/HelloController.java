package ru.sysout.sec4.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user")
    public String user(Authentication authentication) {
        System.out.println((UserDetails)authentication.getPrincipal());
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

}
