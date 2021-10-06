package ru.sysout.dao;

import ru.sysout.model.Post;

import java.util.List;

public interface CustomPostRepository {
    List<Post> findMySuperPosts(String title, long id);
    List<Post> findMySuperPostsWithJoin(String title, long id);
    long updatePosts(String oldTitle, String title, long userId);
    long deletePosts( long userId);
}
