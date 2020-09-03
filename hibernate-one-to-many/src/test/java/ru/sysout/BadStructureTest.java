package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.bad.dao.CommentRepository;
import ru.sysout.bad.dao.TopicRepository;
import ru.sysout.bad.model.Comment;
import ru.sysout.bad.model.Topic;

@DataJpaTest
public class BadStructureTest {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("при добавлении топика для каждого комментария выполняется insert и update")
    public void whenAddTopicWithComments_thenInsertsWithUpdates() {
        Topic topic = new Topic("title");
        topic.getComments().add(new Comment("c1"));
        topic.getComments().add(new Comment("c2"));
        topic.getComments().add(new Comment("c3"));
        topic = topicRepository.save(topic);

        Assertions.assertEquals(4, topicRepository.count());
    }

    @Test
    @DisplayName("при удалении комментария для него выполняется update и delete")

    public void whenDeleteComment_thenDeleteWithUpdate() {
       Topic topic = topicRepository.getOne(-1l);
       topic.getComments().remove(0);

       Assertions.assertEquals(2, commentRepository.count());
    }
}
