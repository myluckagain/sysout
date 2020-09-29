package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dto.CityDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class AppTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void whenGetDtosWithCollectionUsingResultTransformer_ThenOk() {
        List<CityDto> cityDtos = em.createQuery("select c.id as c_id,\n" +
                "           c.name as c_name,\n" +
                "           d.id as d_id,\n" +
                "           d.name as d_name\n" +
                "    from District d\n" +
                "    join d.city c\n" +
                "    order by d.id")
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new CityDtoResultTransformer())
                .getResultList();

        Assertions.assertEquals(5, cityDtos.size());
    }

}
