package ru.sysout.projections.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.projections.dto.PostView;
import ru.sysout.projections.dto1.PostView1;
import ru.sysout.projections.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostView> findByTitle(String title);

    @Query("select p from Post p join fetch p.user where p.title=:title" )
    List<PostView> findByTitleJoin(String title);

    @Query(nativeQuery = true, value = "select p.id as id, p.title as title, " +
            "u.id as userId, u.nickname as nickname, u.email as email, u.password as password  "+
            "from post as p join user as u on p.user_id=u.id where p.title=:title")
    List<PostView1> findByTitleNativeJoin(String title);

}
