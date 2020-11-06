package ru.sysout.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(columnDefinition="text")
    private String name;
    @Column(columnDefinition="text")
    private String htmlUrl;
    @Column(columnDefinition="text")
    private String description;

}
