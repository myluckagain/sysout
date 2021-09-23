package ru.sysout.projections.dao;

import ru.sysout.projections.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Object[]> findUsersCommon() {
        List<Object[]> results = this.em.createNativeQuery(
                "select id, email,  nickname, password,  role,  locked  from user ")
                .getResultList();
        return results;
    }


    @Override
    public List<User> findUsersDefaultMapping() {
        List<User> results = this.em.createNativeQuery(
                "select u.id as userid, u.email,  u.nickname,  u.password,  u.role,  u.locked  from user u",
                User.class).getResultList();
        return results;
    }

    @Override
    public List<Tuple> findUsersWithPostCount() {
        List<Tuple> results = this.em.createNamedQuery("UsersWithPostCount", Tuple.class).getResultList();
        return results;
    }



}
