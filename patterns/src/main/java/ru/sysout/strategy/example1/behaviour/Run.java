package ru.sysout.strategy.example1.behaviour;

public class Run implements MoveBehaviour {
    @Override
    public void move() {
        System.out.println("run");
    }
}
