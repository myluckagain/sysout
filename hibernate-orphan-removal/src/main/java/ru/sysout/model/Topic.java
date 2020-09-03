package ru.sysout.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();
    public Topic(String title){
        this.title=title;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setTopic(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setTopic(null);
    }
}
