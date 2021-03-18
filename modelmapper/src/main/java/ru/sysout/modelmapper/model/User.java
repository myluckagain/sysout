package ru.sysout.modelmapper.model;


import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private String role="ROLE_USER";

    private boolean locked=false;

}
