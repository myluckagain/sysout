package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest

class FirstLevelCacheTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void when2Finds_thenOnefind() {
        City city=em.find(City.class,1l);
        System.out.println("find1");
        Assertions.assertEquals("city1", city.getName());
        City city2=em.find(City.class,1l);
        System.out.println("find2");
        Assertions.assertEquals("city1", city.getName());
    }



}
