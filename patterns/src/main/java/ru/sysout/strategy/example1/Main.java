package ru.sysout.strategy.example1;

import ru.sysout.strategy.example1.behaviour.*;
import ru.sysout.strategy.example1.entities.*;

public class Main {
    public static void main(String[] args) {
        MoveBehaviour run = new Run();
        MoveBehaviour fly = new Fly();
        MoveBehaviour noMove = new NoMove();

        EatBehaviour eatGrass = new EatGrass();
        EatBehaviour eatMeet = new EatMeet();
        EatBehaviour noEat = new NoEat();

        Animal bird = new Bird();
        bird.setEatBehaviour(eatGrass);
        bird.setMoveBehaviour(fly);
        bird.doEat();
        bird.doMove();

        Animal tiger = new Tiger();
        tiger.setMoveBehaviour(run);
        tiger.setEatBehaviour(eatMeet);
        tiger.doEat();
        tiger.doMove();

        Animal deer = new Deer();
        deer.setEatBehaviour(eatGrass);
        deer.setMoveBehaviour(run);
        deer.doEat();
        deer.doMove();

        Animal toyAnimal = new ToyAnimal();
        toyAnimal.setEatBehaviour(noEat);
        toyAnimal.setMoveBehaviour(noMove);
        toyAnimal.doMove();
        toyAnimal.doEat();
    }
}
