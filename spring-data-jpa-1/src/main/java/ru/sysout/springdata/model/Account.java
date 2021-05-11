package ru.sysout.springdata.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private long amount;

    @ManyToOne
    private User user;

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(String name) {
        this.name = name;
    }
}
