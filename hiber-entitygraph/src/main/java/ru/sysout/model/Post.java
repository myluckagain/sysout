package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "post-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "images")
                }
        )
})
@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ElementCollection//(fetch = FetchType.EAGER)
    private Set<String> tags = new HashSet<>();

    public Post(String title) {
        this.title = title;
    }

    public void addImage(Image image) {
        image.setPost(this);
        this.images.add(image);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", images=" + images +
                ", tags=" + tags +
                '}';
    }
}
