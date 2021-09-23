package ru.sysout.projections.dao;

import ru.sysout.projections.dto.PostDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PostDto> findPostDtos() {
        List<PostDto> results=em.createNamedQuery("PostDtos").getResultList();
        return results;
    }



}
