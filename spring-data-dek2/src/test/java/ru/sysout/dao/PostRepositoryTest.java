package ru.sysout.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.model.Image;
import ru.sysout.model.Post;
import ru.sysout.service.PostService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest

public class PostRepositoryTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public   void createPosts() {

            for (int i = 0; i < 5; i++) {
                Post post = new Post("topic" + i);
                Image image1 = new Image("url1_" + i);
                Image image2 = new Image("url2_" + i);
                post.addImage(image1);
                post.addImage(image2);
                Set<String> tags = Arrays.asList("red", "green", "blue", "orange", "white").stream().collect(Collectors.toSet());
                post.setTags(tags);
                postRepository.save(post);
            }
    }
    @Test
    public void shouldGet(){
       List<Post> posts= postService.getPosts();
       for(Post post:posts) System.out.println(post.getTitle()+" "
               +post.getTags().size()+" "
               +post.getImages().size());
    }

}
