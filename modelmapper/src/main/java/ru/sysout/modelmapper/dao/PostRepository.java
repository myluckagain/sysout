package ru.sysout.modelmapper.dao;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.modelmapper.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query(value = "SELECT p FROM Post p")
    List<Post> findAll();



}
