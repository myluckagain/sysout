package ru.javalang.hamcrest;

public class Dog extends Animal {

    public Dog(String sound) {
        super(sound);

    }

    public String toString() {
        return this.sound;
    }
}
