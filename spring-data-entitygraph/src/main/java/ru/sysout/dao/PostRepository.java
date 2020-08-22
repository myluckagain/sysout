package ru.sysout.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @EntityGraph(value = "post-entity-graph")
    List<Post> findByTitle(String title);

    @EntityGraph(attributePaths = {"tags"})
    Optional<Post> findById(Long id);

    @EntityGraph(value = "post-entity-graph")
    @Query("SELECT p FROM Post p where p.id=:id")
    Post getPostWithImages(Long id);


}
