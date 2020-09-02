package ru.sysout.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;


}
