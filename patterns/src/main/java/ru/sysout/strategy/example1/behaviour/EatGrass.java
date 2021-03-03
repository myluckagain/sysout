package ru.sysout.strategy.example1.behaviour;

public class EatGrass implements EatBehaviour {
    @Override
    public void eat() {
        System.out.println("grass");
    }
}
