package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@SecondaryTable(name = "UserDetails",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id"))
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    @Column(name="phone", table="UserDetails")
    private String phone;

    public User(String name) {
        this.name = name;
    }
}
