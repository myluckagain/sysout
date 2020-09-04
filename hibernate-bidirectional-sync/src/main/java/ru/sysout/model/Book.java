package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors=new HashSet<>();

    public Book(String name){
        this.name=name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Author)) return false;

        return id != null && id.equals(((Book) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
