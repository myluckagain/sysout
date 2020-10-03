package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class SecondLevelCacheTest {

    @PersistenceContext
    private EntityManager em;


    @Test
    @Transactional
    public void find1() {
       City city=em.find(City.class,1l);
        System.out.println("select1");
        Assertions.assertEquals("city1", city.getName());
    }

    @Test
    @Transactional
    public void find2() {
        City city=em.find(City.class,1l);
        System.out.println("select2");
        Assertions.assertEquals("city1", city.getName());
    }

}
