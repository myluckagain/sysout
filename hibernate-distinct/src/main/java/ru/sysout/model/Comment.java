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
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;

    public Comment(String text) {
        this.text = text;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Comment)) return false;
        return id != null && id.equals(((Comment) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
