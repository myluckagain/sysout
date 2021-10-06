package ru.sysout.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "sequence")
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String role = "ROLE_USER";

    private boolean locked = false;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Set<Post> posts = new HashSet<>();
}
