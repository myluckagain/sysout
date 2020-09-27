package ru.sysout.springdataentitygraph;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.City;
import ru.sysout.model.District;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
@Transactional
class BatchTest {

    @PersistenceContext
    private EntityManager em;
    private static final int BATCH_SIZE = 5;


    @Test
    public void whenNotConfigured_ThenSendsInsertsSeparately() {
        for (int i = 0; i < 10; i++) {
            City city = new City(String.valueOf("city" + i));
            em.persist(city);
        }
        em.flush();
    }


    @Test
    public void whenFlushingAfterBatch_ThenClearsMemory() {
        for (int i = 0; i < 10; i++) {
            City city = new City(String.valueOf(i));
            em.persist(city);

            if ((i + 1) % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
    }


    @Test
    public void whenThereAreMultipleEntities_ThenCreatesNewBatch() {
        for (int i = 0; i < 10; i++) {

            City city = new City(String.valueOf(i));

            District first = new District(city);
            District second = new District(city);
            city.addDistrict(first);
            city.addDistrict(second);
            em.persist(city);


            if ((i + 1) % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
    }


    @Test
    @Commit
    public void whenUpdatingEntities_thenCreatesBatch() {
        TypedQuery<City> query =
                em.createQuery("SELECT c from City c", City.class);
        List<City> cities = query.getResultList();
        for (City city : cities) {
            city.setName("new " + city.getName());
            city.getDistricts().get(0).setName("new d1");
            city.getDistricts().get(1).setName("new d2");
        }
    }
}
