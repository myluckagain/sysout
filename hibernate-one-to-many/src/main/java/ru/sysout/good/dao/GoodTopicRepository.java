package ru.sysout.good.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.good.model.GoodTopic;

public interface GoodTopicRepository extends JpaRepository<GoodTopic, Long> {
}
