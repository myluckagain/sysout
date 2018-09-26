package ru.sysout.accessmodifiers.priv;

import ru.sysout.accessmodifiers.priv.A;

public class A {
    private int privateVar = 1;

    private void privateMethod() {
        System.out.println("A private method is printing " + this.privateVar);
    }
    public static void main(String[] args) {
        A a = new A();
        a.privateVar = 2;
        a.privateMethod();
    }

 
    boolean isEqualTo(A anotherA) {
        if (this.privateVar == anotherA.privateVar)
            return true;
        else
            return false;
    }
}
