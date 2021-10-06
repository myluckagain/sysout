package ru.sysout.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.sysout.model.Post;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostRepositoryTest {
    @Autowired
    CustomPostRepository postRepository;

    @Test
    public void shouldFindPosts() {
        List<Post> posts = postRepository.findMySuperPosts("S%", 1l);
        Assertions.assertEquals(3, posts.size());
    }

    @Test
    public void shouldFindPostsWithJoin() {
        List<Post> posts = postRepository.findMySuperPostsWithJoin("S%", 1l);
        Assertions.assertEquals(3, posts.size());
    }



    @Test
    public void shouldUpdatePosts() {
        long n= postRepository.updatePosts("S%","new title", 1l);
        Assertions.assertEquals(3, n);
    }

    @Test
    public void shouldDeletePosts() {
        long n= postRepository.deletePosts( 1l);
        Assertions.assertEquals(3, n);
    }
}
