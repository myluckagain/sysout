package ru.sysout.accessmodifiers.prot.sub;

import ru.sysout.accessmodifiers.prot.A;

public class C extends A {
    void testAccess(A a, C c) {
        
        this.protectedVar = 1;// legal
        this.protectedMethod();// legal
        
        // a.protectedVar = 10; // illegal
        c.protectedVar = 10; // legal
        // a.protectedMethod(); // illegal
        c.protectedMethod(); // legal
    }
}
