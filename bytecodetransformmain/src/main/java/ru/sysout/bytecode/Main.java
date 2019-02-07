package ru.sysout.bytecode;


public class Main {
    public static void main(String... args) {
        new Main().method();
    }

    @Log(message = "method called")
    private void method() {
    }
}
