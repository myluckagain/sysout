package ru.sysout.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.Post;
import ru.sysout.model.QPost;
import ru.sysout.model.QUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomPostRepositoryImpl implements CustomPostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> findMySuperPosts(String title, long id) {
       JPAQueryFactory queryFactory=new JPAQueryFactory(entityManager);
        QPost post=QPost.post;
        List<Post> posts=queryFactory.selectFrom(post)
                .where(post.user.id.eq(id).and(post.title.like(title)))
                .fetch();
        return posts;
    }

    public List<Post> findMySuperPostsWithJoin(String title, long id) {


        JPAQueryFactory queryFactory=new JPAQueryFactory(entityManager);
        QPost post=QPost.post;
        QUser user=QUser.user;
        List<Post> posts=queryFactory.selectFrom(post).innerJoin(post.user, user).fetchJoin()
                .where(post.user.id.eq(id).and(post.title.like(title)))
                .fetch();
        return posts;
    }

    public long updatePosts(String oldTitle, String title, long userId){
        JPAQueryFactory queryFactory=new JPAQueryFactory(entityManager);
        QPost post=QPost.post;
        QUser user=QUser.user;
        return queryFactory.update(post)
                .where(post.user.id.eq(userId).and(post.title.like(oldTitle)))
                .set(post.title, title)
                .execute();

    }

    public long deletePosts( long userId){
        JPAQueryFactory queryFactory=new JPAQueryFactory(entityManager);
        QPost post=QPost.post;
        QUser user=QUser.user;
        return queryFactory.delete(post)
                .where(post.user.id.eq(userId))
                .execute();

    }
}
