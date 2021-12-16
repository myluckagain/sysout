package ru.sysout.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    public Customer(String name){
        this.name =name;
    }
}
