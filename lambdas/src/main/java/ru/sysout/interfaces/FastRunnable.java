package ru.sysout.interfaces;

@FunctionalInterface
public interface FastRunnable extends Runnable {
    default void fastrun(){
        System.out.println("run fast");
    }
}
