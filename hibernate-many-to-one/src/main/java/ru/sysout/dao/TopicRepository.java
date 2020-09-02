package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
