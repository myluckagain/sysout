package ru.sysout.dockerdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String name;
}
