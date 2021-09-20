package ru.sysout.projections.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(generator = "sequence")
    private Long id;

    private String title;

    private String text;


    @ManyToOne
    private User user;
}
