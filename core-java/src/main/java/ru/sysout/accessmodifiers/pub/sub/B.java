package ru.sysout.accessmodifiers.pub.sub;

import ru.sysout.accessmodifiers.pub.A;

public class B {
    void testAccess() {
        A a = new A();
        a.publicVar = 10; // legal
        a.publicMethod(); // legal
    }
}
