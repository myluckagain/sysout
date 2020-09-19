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
public class OneToManyCascadeTest {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("добавление топика с комментариями")
    public void whenAddTopic_commentsShouldBeAdded() {

        Topic topic = new Topic("Topic1");
        Comment comment1 = new Comment("comment1");
        Comment comment2 = new Comment("comment2");
        topic.addComment(comment1);
        topic.addComment(comment2);
        topicRepository.save(topic);
        Assertions.assertEquals(2, topicRepository.count());
        Assertions.assertEquals(5, commentRepository.count());
    }

    @Test
    @DisplayName("редактирование топика с комментариями")
    public void whenMergeTopic_commentsShouldBeMerged() {
        Topic topic = topicRepository.getOne(-1l);
        topic.setTitle("Updated Title");
        Comment comment = commentRepository.getOne(-4l);
        comment.setText("Updated Text");
        topicRepository.save(topic);
        Assertions.assertEquals(3, commentRepository.count());
    }

    @Test
    @DisplayName("удаление топика с комментариями")
    public void whenDeleteTopic_commentsShouldBeDeleted() {
        Topic topic = topicRepository.getOne(-1l);
        topicRepository.delete(topic);
        Assertions.assertEquals(0, commentRepository.count());
    }

    @Test
    @DisplayName("если orphanRomoval=true, то при удалении комментария из топика он удаляется из базы")
    public void givenOrphanRomovalTrue_whenRemoveCommentFromTopic_thenItRemovedFromDatabase() {
        Topic topic = topicRepository.getOne(-1l);
        topic.removeComment(topic.getComments().get(0));
        Assertions.assertEquals(2, commentRepository.count());
    }
}
