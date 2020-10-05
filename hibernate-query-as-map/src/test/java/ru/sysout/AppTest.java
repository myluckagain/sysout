package ru.sysout;

import com.vladmihalcea.hibernate.type.util.MapResultTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest

class AppTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void test1() {
       List<Tuple> list=em.createQuery("select year(i.localDate) as year, sum(i.sum) as sum from Income i group by year")
                .getResultList();
       Assertions.assertEquals(4, list.size());
    }

    @Test
    @Transactional
    public void test2() {
        Map<Integer, Integer> map=em.createQuery("select year(i.localDate) as year, sum(i.sum) as sum from Income i group by year", Tuple.class)
                .getResultStream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((Number) tuple.get("year")).intValue(),
                                tuple -> ((Number) tuple.get("sum")).intValue()
                        )
                );
        Assertions.assertEquals(4, map.values().size());
    }


    @Test
    @Transactional
    public void test3() {
        Map<Integer, Integer> map=em.createQuery("select year(i.localDate) as year, sum(i.sum) as sum from Income i group by year", Tuple.class)
                .getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((Number) tuple.get("year")).intValue(),
                                tuple -> ((Number) tuple.get("sum")).intValue()
                        )
                );
        Assertions.assertEquals(4, map.values().size());
    }

    @Test
    @Transactional
    public void test4() {
        Map<Integer, Integer> map= (Map<Integer, Integer>) em.createQuery("select year(i.localDate) as year, sum(i.sum) as sum from Income i group by year")
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(
                        new MapResultTransformer<>()
                )
                .getSingleResult();
        Assertions.assertEquals(4, map.values().size());
    }
}
