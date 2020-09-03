package ru.sysout.bad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.bad.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
