package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import ru.sysout.model.Topic;

import javax.persistence.QueryHint;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @QueryHints(value = { @QueryHint(name = "hibernate.query.passDistinctThrough", value = "false")})
    @Query("select distinct t from Topic t  left join fetch t.comments")
    List<Topic> getTopicsWithComments();

     @Query("select distinct t.title from Topic t")
     List<String> getUniqueTopicTitles();
}
