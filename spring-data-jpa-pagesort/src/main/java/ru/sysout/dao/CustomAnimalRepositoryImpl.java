package ru.sysout.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.sysout.model.Animal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomAnimalRepositoryImpl implements CustomAnimalRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Animal> getAnimals(Pageable pageable) {
        Query query = em.createQuery("select a from Animal a");
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<Animal> animals = query.getResultList();

        Query queryCount = em.createQuery("Select count(id) From Animal a");
        long count = (long) queryCount.getSingleResult();

        return new PageImpl<Animal>(animals, pageable, count);
    }
}
