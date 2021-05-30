package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.model.Comment;
import ru.sysout.service.CommentService;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("если метод FindById, то select и insert")
    public void whenFindUser_ThenLazy() {
        Comment comment = commentService.addToTopicUsingFindById(-1L, "text");
        Assertions.assertEquals("text", comment.getText());
    }

    @Test
    @DisplayName("если метод GetById, то один insert")
    public void givenGetOne_whenAddToTopic_thenEfficient() {
        Comment comment = commentService.addToTopicUsingGetById(-1L, "text");
        Assertions.assertEquals("text", comment.getText());
    }

}
