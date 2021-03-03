package ru.sysout.strategy.example2.behaviour;

public class Knife implements WeaponBehaviour {
    @Override
    public void useWeapon() {
        System.out.println("knife");
    }
}
