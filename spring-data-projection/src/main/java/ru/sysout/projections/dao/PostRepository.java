package ru.sysout.projections.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.projections.dto.PostView;
import ru.sysout.projections.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostView> findByTitle(String title);
}
