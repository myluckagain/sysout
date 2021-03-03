package ru.sysout.strategy.example1.behaviour;

public class EatMeet implements EatBehaviour {
    @Override
    public void eat() {
        System.out.println("meet");
    }
}
