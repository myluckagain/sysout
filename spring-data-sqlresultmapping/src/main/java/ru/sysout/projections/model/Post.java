package ru.sysout.projections.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sysout.projections.dto.PostDto;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@NamedNativeQuery(name = "PostDtos", query = "select p.id, p.title, " +
        "u.id as userId, u.nickName  " +
        "from post as p join user as u on p.user_id=u.id ", resultSetMapping = "PostDtoMapping")
@SqlResultSetMapping(name = "PostDtoMapping",
        classes = {
                @ConstructorResult(
                        columns = {
                                @ColumnResult(name = "id", type = long.class),
                                @ColumnResult(name = "title"),
                                @ColumnResult(name = "userId", type = long.class),
                                @ColumnResult(name = "nickname")
                        },
                        targetClass = PostDto.class
                )}
)
public class Post {
    @Id
    @GeneratedValue(generator = "sequence")
    private Long id;

    private String title;

    private String text;

    @ManyToOne
    private User user;
}
