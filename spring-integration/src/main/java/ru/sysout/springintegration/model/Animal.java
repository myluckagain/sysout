package ru.sysout.springintegration.model;

public class Animal {

    private String name;

    public Animal(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
