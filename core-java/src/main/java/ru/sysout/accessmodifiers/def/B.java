package ru.sysout.accessmodifiers.def;

public class B {
    void testAccess() {
        A a = new A();
        a.defaultVar = 10; // legal
        a.defaultMethod(); // legal
    }
}
