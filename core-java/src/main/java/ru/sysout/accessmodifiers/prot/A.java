package ru.sysout.accessmodifiers.prot;

public class A {
    protected int protectedVar = 1;

    protected void protectedMethod() {
        System.out.println("A protected method is printing " + this.protectedVar);
    }

}
