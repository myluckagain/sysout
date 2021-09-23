package ru.sysout.projections.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "UsersWithPostCount",
        query = "select count(p.id) as post_count, " +
        "u.* " +
        "from  user as u  left join post as p on p.user_id=u.id group by u.id",
        resultSetMapping = "UsersWithPostCountMapping")
@SqlResultSetMapping(name = "UsersWithPostCountMapping",
        entities = @EntityResult(
                entityClass = User.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "email", column = "email"),
                        @FieldResult(name = "nickname", column = "nickname"),
                        @FieldResult(name = "password", column = "password"),
                        @FieldResult(name = "role", column = "role"),
                        @FieldResult(name = "locked", column = "locked"),
                }
        ),

        columns = @ColumnResult(
                name = "post_count",
                type = int.class
        )
)
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
