package ru.sysout.dao;


import ru.sysout.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> findByTitleDesc(String title) {
       return  em.createQuery("SELECT p FROM Post p WHERE p.title = :title order by p.title desc", Post.class)
                .setParameter("title", title)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("post-entity-graph"))
                .getResultList();
    }
}
