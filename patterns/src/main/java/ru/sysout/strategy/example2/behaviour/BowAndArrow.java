package ru.sysout.strategy.example2.behaviour;

public class BowAndArrow implements WeaponBehaviour {
    @Override
    public void useWeapon() {
        System.out.println("bowAndArrow");
    }
}
