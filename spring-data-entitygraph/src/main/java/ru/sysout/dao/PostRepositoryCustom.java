package ru.sysout.dao;

import ru.sysout.model.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findByTitleDesc(String title);
}
