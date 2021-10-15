package ru.sysout.dao;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.Hits;

@Transactional(propagation = Propagation.REQUIRED)
@Repository
public interface HitRepository extends CrudRepository<Hits, Long> {
      @Query( "select id, count from hits where id=:id")
      Hits getCount(long id);

      @Modifying
      @Query("update hits set count=count+1 where id=:id")
      void updateCount(long id);

}
