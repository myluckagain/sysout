package ru.sysout.strategy.example1.entities;

import ru.sysout.strategy.example1.behaviour.EatBehaviour;
import ru.sysout.strategy.example1.behaviour.MoveBehaviour;

public abstract class Animal {

    private MoveBehaviour moveBehaviour;
    private EatBehaviour eatBehaviour;

    public void doMove() {
        this.moveBehaviour.move();
    }

    public void doEat() {
        this.eatBehaviour.eat();
    }

    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public void setEatBehaviour(EatBehaviour eatBehaviour) {
        this.eatBehaviour = eatBehaviour;
    }

}
