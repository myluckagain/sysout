package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.CommentRepository;
import ru.sysout.dao.TopicRepository;
import ru.sysout.model.Topic;

@DataJpaTest
public class GoodTest {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("если orphanRomoval=true, то при удалении комментария из топика он удаляется из базы")
    public void givenOrphanRomovalTrue_whenRemoveCommentFromTopic_thenItRemovedFromDatabase() {
        Topic topic = topicRepository.getById(-1l);
        topic.removeComment(topic.getComments().get(0));

        Assertions.assertEquals(2, commentRepository.count());
    }

/*
    @Test
    @DisplayName("если orphanRomoval=false, то при удалении комментария из топика остается в базе")
    public void givenOrphanRomovalFalse_whenRemoveCommentFromTopic_thenItRemovedFromDatabase() {
        Topic topic = topicRepository.getById(-1l);
        topic.removeComment(topic.getComments().get(0));
        Assertions.assertEquals(3, commentRepository.count());
    }
*/

    @Test
    @DisplayName("если CascadeType=REMOVE, то при удалении из базы топика удаляются его комментарии")
    public void givenCascadeTypeIsRemove_whenRemoveTopic_thenCommentsRemoved() {
        Topic topic = topicRepository.getById(-1l);
        topicRepository.delete(topic);
        Assertions.assertEquals(0, commentRepository.count());
    }

}
