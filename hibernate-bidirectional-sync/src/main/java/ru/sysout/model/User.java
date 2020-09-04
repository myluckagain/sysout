package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name ="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private UserDetails details;

     public void setDetails(UserDetails details) {
        if (details == null) {
            if (this.details != null) {
                this.details.setUser(null);
            }
        }
        else {
            details.setUser(this);
        }
        this.details = details;
    }
}
