package ru.sysout.aspectsdemo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import ru.sysout.aspectsdemo.service.FullNameComposer;
import ru.sysout.aspectsdemo.service.SomeService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AspectsDemoApplication implements CommandLineRunner {
    @Autowired
    FullNameComposer composer;
    @Autowired
    SomeService service;
    public static void main(String[] args) {
        SpringApplication.run(AspectsDemoApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        composer.composeFullName("Ivan", "Petrov");
        service.someMethod();
    }
}