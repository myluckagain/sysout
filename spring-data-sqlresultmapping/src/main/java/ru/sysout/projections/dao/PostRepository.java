package ru.sysout.projections.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.projections.dto.PostDto;
import ru.sysout.projections.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    @Query(nativeQuery = true, name = "PostDtos")
    List<PostDto> findDtos();
}
