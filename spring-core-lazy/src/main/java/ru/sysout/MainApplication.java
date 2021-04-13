package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class MainApplication {
    private A a;

    @Autowired
    @Lazy
    public MainApplication(A a) {
        this.a = a;
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = SpringApplication.run(MainApplication.class, args);
        System.out.println("waiting for a second...");
        Thread.sleep(1000);
        A a = applicationContext.getBean(A.class);
    }
}
