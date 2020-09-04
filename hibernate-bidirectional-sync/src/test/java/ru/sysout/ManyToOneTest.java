package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.CommentRepository;
import ru.sysout.dao.TopicRepository;
import ru.sysout.model.Comment;
import ru.sysout.model.Topic;

@DataJpaTest
public class ManyToOneTest {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    @DisplayName("добавление комментариев")
    public void commentsShouldBeAdded() {

        Topic topic = new Topic("Topic1");
        Comment comment1 = new Comment("comment1");
        Comment comment2 = new Comment("comment1");
        topic.addComment(comment1);
        topic.addComment(comment2);
        topicRepository.save(topic);
        Assertions.assertEquals(2, topicRepository.count());
        Assertions.assertEquals(5, commentRepository.count());
    }

    @Test
    @DisplayName("удаление комментария")
    public void commentsShouldBeDeleted() {
        Topic topic = topicRepository.getOne(-1l);
        topic.removeComment(topic.getComments().get(0));
        Assertions.assertEquals(2, commentRepository.count());
    }
}
