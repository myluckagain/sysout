package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sysout.service.UserService;

@SpringBootApplication
public class SpringDataJpaApplication {
    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }


}
