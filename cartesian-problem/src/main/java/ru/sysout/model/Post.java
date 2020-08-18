package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/)
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
}
