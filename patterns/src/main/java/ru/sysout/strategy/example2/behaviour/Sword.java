package ru.sysout.strategy.example2.behaviour;

public class Sword implements WeaponBehaviour {
    @Override
    public void useWeapon() {
        System.out.println("sword");
    }
}
