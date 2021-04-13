package ru.sysout;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class A {
    public A(){
        System.out.println("A constructor");
    }
}
