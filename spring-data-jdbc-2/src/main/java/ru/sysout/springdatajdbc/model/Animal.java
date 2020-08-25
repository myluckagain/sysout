package ru.sysout.springdatajdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Animal {
    @Id
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
