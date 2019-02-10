package ru.sysout.annotationsruntime;

public class Sample1 {
    @Test
    public static void m1() {
    } // Test should pass

    public static void m2() {
    }

    @Test
    public static void m3() { // Test should fail
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() { // Test should fail
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }

    @Test
    public void m5() {

    } // INVALID USE: nonstatic method
}