package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MainApplication  {
    @Value("${prop1}")
    private String prop1;

    @Value("${TEMP}")
    private String temp;

    @Value("${app.n}")
    private int n;
    @Value("${app.m}")
    private int m;

    @Autowired
    private Environment environment;

    @Autowired
    private AppProperties appProperties;

    @PostConstruct
    void print(){

        System.out.println(n);
        System.out.println(m);
        System.out.println(temp);
        System.out.println(prop1);

        System.out.println(appProperties.getN());
        System.out.println(appProperties.getM());

    }


    public static void main(String[] args) throws InterruptedException {
             SpringApplication.run(MainApplication.class, args);

    }
}
