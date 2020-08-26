package ru.sysout.springdatajdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Animal {

    private long id;
    private String name;

    public Animal(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Animal(String name) {
        this.name = name;
    }
}
