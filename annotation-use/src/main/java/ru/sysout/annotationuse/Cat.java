package ru.sysout.annotationuse;

import ru.sysout.annotationprocessing.ToString;

public class Cat {
    String breed;

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    @ToString
    public String getBreed() {
        return breed;
    }

    @ToString
    public String getName() {
        return name;
    }
}
