package ru.sysout.springdataentitygraph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import ru.sysout.model.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Commit
class FlushTest {

    @PersistenceContext
	private EntityManager em;

	@Test
	@DisplayName("")
	public void whenPersistAndRemove_thenPersistsAreFisrt() {

		City city=new City("Moscow");
		em.persist(city);
		em.remove(city);
		//Без flush имеем  org.hibernate.exception.ConstraintViolationException : Нарушение уникального индекса или первичного ключа
		em.flush();
        em.persist(new City("Moscow"));
	}




}
