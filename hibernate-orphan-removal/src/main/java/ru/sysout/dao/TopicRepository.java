package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sysout.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("select t from Topic t join fetch t.comments where t.id=:id")
    Topic getById(long id);
}
