package ru.sysout.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document implements IEntity {

    @Id
    private Integer id;

    private String content;

    public Document(String content) {
        this.content=content;
    }
}
