package ru.sysout.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("select p from Post p where p.user.id=:id and p.title like :title")
    List<Post> findMySuperPosts(String title, long id);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select * from post p where p.user_id=:id and p.title like :title")
    List<Post> findMySuperPostsNative(String title, long id);

    @Transactional(readOnly = true)
    @Query("select p from Post p where p.title like :title")
    List<Post> findSuperPosts(String title, Pageable pageable);


    @Modifying
    @Transactional
    @Query("update Post p set p.title=:title where p.id=:id")
    int updatePostTitlesFor(String title, long id);


    @Modifying
    @Transactional
    @Query("delete from Post p where p.user.id=:id")
    int deleteInBulkByUserId(long id);

    @Transactional
    int deleteByUserId(long id);


}
