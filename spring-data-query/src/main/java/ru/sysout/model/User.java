package ru.sysout.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "sequence")
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String role = "ROLE_USER";

    private boolean locked = false;

}
