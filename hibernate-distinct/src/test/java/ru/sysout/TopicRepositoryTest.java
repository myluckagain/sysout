package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.TopicRepository;
import ru.sysout.model.Topic;

import java.util.List;

@DataJpaTest
public class TopicRepositoryTest {
    @Autowired
    private TopicRepository topicRepository;



    @Test
    public void whenFindwithDistinct_thenNoDuplicates() {
        List<Topic> topics = topicRepository.getTopicsWithComments();
        Assertions.assertEquals(3, topics.size());
    }

    @Test
    public void whenGetTopicNames_thenSQLDistinct() {
        List<String> titles = topicRepository.getUniqueTopicTitles();
        Assertions.assertEquals(2, titles.size());
    }
}
