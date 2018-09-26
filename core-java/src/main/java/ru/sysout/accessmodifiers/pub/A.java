package ru.sysout.accessmodifiers.pub;

public class A {
    public int publicVar = 1;

    public void publicMethod() {
        System.out.println("A public method is printing " + this.publicVar);
    }

}
