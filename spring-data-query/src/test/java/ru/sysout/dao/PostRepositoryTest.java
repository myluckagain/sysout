package ru.sysout.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import ru.sysout.model.Post;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @DisplayName("поиск постов с @Query")
    @Test
    @DirtiesContext
    void shouldFindPosts() {
        List<Post> posts = postRepository.findMySuperPosts("Super%", 1l);
        Assertions.assertEquals(3, posts.size());
    }

    @DisplayName("поиск постов с native @Query")
    @Test
    @DirtiesContext
    void shouldFindPostsUsingNative() {
        List<Post> posts = postRepository.findMySuperPostsNative("Super%", 1l);
        Assertions.assertEquals(3, posts.size());
    }

    @DisplayName("поиск постов с @Query постранично")
    @Test
    @DirtiesContext
    void shouldFindPagablrPosts() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("title"));
        List<Post> posts = postRepository.findSuperPosts("Super%", pageable);
        Assertions.assertEquals(2, posts.size());
    }


    @DisplayName("редактирование title с @Modifying @Query")
    @Test
    @DirtiesContext
    void titleShouldBeUpdated() {
        int n = postRepository.updatePostTitlesFor("333", 3l);
        Assertions.assertEquals(1, n);
    }


    @DirtiesContext
    @DisplayName("посты должны удаляться  по user.id единым запросом в @Query")
    @Test
    void titlesShouldBeDeletedByIdInBulk() {
        int n = postRepository.deleteInBulkByUserId(1l);
        Assertions.assertEquals(3, n);
    }


    @DirtiesContext
    @DisplayName("посты должны удаляться по user.id  по одному в derived query method")
    @Test
    void titlesShouldBeDeletedByIdByOne() {
        int n = postRepository.deleteByUserId(1l);
        Assertions.assertEquals(3, n);
    }


}
