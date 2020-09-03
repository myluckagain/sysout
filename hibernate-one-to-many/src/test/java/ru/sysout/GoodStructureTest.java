package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.good.dao.GoodCommentRepository;
import ru.sysout.good.dao.GoodTopicRepository;
import ru.sysout.good.model.GoodComment;
import ru.sysout.good.model.GoodTopic;

@DataJpaTest
public class GoodStructureTest {
    @Autowired
    private GoodTopicRepository topicRepository;
    @Autowired
    private GoodCommentRepository commentRepository;

    @Test
    @DisplayName("при добавлении топика для каждого комментария выполняется один insert")
    public void whenAddTopicWithComments_thenInsertsWithUpdates() {
        GoodTopic topic = new GoodTopic("title");
        topic.addComment(new GoodComment("c1"));
        topic.addComment(new GoodComment("c2"));
        topic.addComment(new GoodComment("c3"));
        topic = topicRepository.save(topic);

        Assertions.assertEquals(4, topicRepository.count());
    }

    @Test
    @DisplayName("при удалении комментария для него выполняется один delete")

    public void whenDeleteComment_thenDeleteWithUpdate() {
       GoodTopic topic = topicRepository.getOne(-11l);
       topic.removeComment(topic.getComments().get(0));

       Assertions.assertEquals(2, commentRepository.count());
    }
}
