package ru.sysout.strategy.example2.behaviour;

public class Axe implements WeaponBehaviour {
    @Override
    public void useWeapon() {
        System.out.println("axe");
    }
}
