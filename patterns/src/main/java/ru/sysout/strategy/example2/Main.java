package ru.sysout.strategy.example2;

import ru.sysout.strategy.example2.behaviour.*;
import ru.sysout.strategy.example2.entities.Character;
import ru.sysout.strategy.example2.entities.*;

public class Main {
    public static void main(String[] args) {
        WeaponBehaviour sword = new Sword();
        WeaponBehaviour knife = new Knife();
        WeaponBehaviour axe = new Axe();
        WeaponBehaviour bowAndArrow = new BowAndArrow();

        Character knight = new Knight();
        knight.setWeaponBehaviour(axe);
        knight.doUseWeapon();

        Character king = new King();
        king.setWeaponBehaviour(knife);
        king.doUseWeapon();

        Character queen = new Queen();
        queen.setWeaponBehaviour(sword);
        queen.doUseWeapon();

        Character troll = new Troll();
        troll.setWeaponBehaviour(bowAndArrow);
        troll.doUseWeapon();
    }
}
