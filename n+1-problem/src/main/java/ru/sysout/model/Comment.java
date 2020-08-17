package ru.sysout.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @ManyToOne//(fetch = FetchType.LAZY)
    private Topic topic;

    public Comment(String text){
        this.text=text;
    }
}
