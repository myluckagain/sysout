package ru.sysout.good.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class GoodComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private GoodTopic topic;

    public GoodComment(String text) {
        this.text = text;
    }

}
