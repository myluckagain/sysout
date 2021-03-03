package ru.sysout.strategy.example1.behaviour;

public class Fly implements MoveBehaviour {
    @Override
    public void move() {
        System.out.println("fly");
    }
}
