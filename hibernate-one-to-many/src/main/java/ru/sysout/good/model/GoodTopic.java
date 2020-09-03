package ru.sysout.good.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class GoodTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoodComment> comments=new ArrayList<>();
    public GoodTopic(String title){
        this.title=title;
    }
    public void addComment(GoodComment comment) {
        comments.add(comment);
        comment.setTopic(this);
    }

    public void removeComment(GoodComment comment) {
        comments.remove(comment);
        comment.setTopic(null);
    }
}
