package ru.sysout.accessmodifiers.def;

public class A {
    int defaultVar = 1;

    void defaultMethod() {
        System.out.println("A private method is printing " + this.defaultVar);
    }

}
