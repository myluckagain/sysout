package ru.sysout.springdatajdbc.dao;

import ru.sysout.springdatajdbc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findUserByHisName(String name) {
        return em.createQuery("select u from User u where u.name =:name", User.class)
                .setParameter("name", name)
                .getResultStream().findAny();
    }

/*    @Override
    public Optional<User> findUseByItsName(String name) {
        return em.unwrap(Session.class).createQuery("select u from User u where u.name =:name", User.class)
                .setParameter("name", name)
                .setFirstResult(0)
                .setMaxResults(1)
                .uniqueResultOptional();
    }*/
}
