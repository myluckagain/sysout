package ru.sysout.accessmodifiers.prot;

public class B {
    void testAccess() {
        A a = new A();
        a.protectedVar = 10; // legal
        a.protectedMethod(); // legal
    }
}
