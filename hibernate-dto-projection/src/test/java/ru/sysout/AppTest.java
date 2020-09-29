package ru.sysout;

import org.hibernate.transform.Transformers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dto.CityDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;

@SpringBootTest
@Transactional
class AppTest {

    @PersistenceContext
    private EntityManager em;


    @Test
    public void whenGetDtosUsingConstructorExpr_ThenOk() {
        List<CityDto> cityDtos = em.createQuery("select new ru.sysout.dto.CityDto(c.id, c.name) from City c", CityDto.class)
                .getResultList();
        Assertions.assertEquals(5, cityDtos.size());
    }

    @Test
    public void whenGetDtosUsingTuple_ThenOk() {
        List<Tuple> cityDtos = em.createQuery("select c.id as id, c.name as name from City c", Tuple.class)
                .getResultList();
        Assertions.assertEquals(5, cityDtos.size());
        Assertions.assertEquals("name1", cityDtos.get(0).get("name"));
    }

    @Test
    public void whenGetDtosUsingNativeSQLAndTuple_ThenOk() {
        List<Tuple> cityDtos = em.createNativeQuery("select c.id as id, c.name as name from City c", Tuple.class)
                .getResultList();
        Assertions.assertEquals(5, cityDtos.size());
        Assertions.assertEquals("name1", cityDtos.get(0).get("name"));
    }

    @Test
    public void whenGetDtosUsingResultTransformer_ThenOk() {
        List<CityDto> cityDtos = em.createQuery("select c.id as id, c.name as name from City c")
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(CityDto.class))
                .getResultList();

        Assertions.assertEquals(5, cityDtos.size());
        Assertions.assertEquals("name1", cityDtos.get(0).getName());
    }


}
