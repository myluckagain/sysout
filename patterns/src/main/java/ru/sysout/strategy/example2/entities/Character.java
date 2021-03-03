package ru.sysout.strategy.example2.entities;

import ru.sysout.strategy.example2.behaviour.WeaponBehaviour;

public abstract class Character {
    private WeaponBehaviour weaponBehaviour;

    public void doUseWeapon() {
        weaponBehaviour.useWeapon();
    }

    public void setWeaponBehaviour(WeaponBehaviour weaponBehaviour) {
        this.weaponBehaviour = weaponBehaviour;
    }
}
