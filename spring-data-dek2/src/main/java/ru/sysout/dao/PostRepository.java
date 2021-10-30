package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.Post;

import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<Post,Long> {

  @Query("select distinct p " +
          "from Post p " +
          "left join fetch p.images "+
          "where p.id between :minId and :maxId ")
    List<Post> findWithImages(long minId, long maxId);

    @Query( "select distinct p " +
            "from Post p " +
            "left join fetch p.tags t " +
            "where p in :posts")
    List<Post> findWithTags(List<Post> posts);

}
