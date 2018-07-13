package ru.javalang.hamcrest;

public class Animal {
    protected String sound;
  

    public String getSound() {
        return sound;
    }


    public void setSound(String sound) {
        this.sound = sound;
    }


    public Animal(String sound) {
        this.sound = sound;
    }
}
