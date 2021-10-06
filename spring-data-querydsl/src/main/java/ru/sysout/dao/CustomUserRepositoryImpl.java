package ru.sysout.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import ru.sysout.model.QUser;
import ru.sysout.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findWithJoin(String title) {
        JPAQueryFactory queryFactory=new JPAQueryFactory(entityManager);
        QUser user= QUser.user;
        List<User> users = queryFactory.selectFrom(user)
                .leftJoin(user.posts).fetchJoin().distinct()
                .fetch();
        return users;
    }
}
