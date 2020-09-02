package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.model.Comment;
import ru.sysout.service.CommentService;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("если метод FindById, то select и insert")
    public void whenFindUser_ThenLazy() {
        Comment comment = commentService.addToTopicUsingFindById(-1l, "text");
        Assertions.assertEquals("text", comment.getText());
    }

    @Test
    @DisplayName("если метод GetOne, то один insert")
    public void givenGetOne_whenAddToTopic_thenEfficient() {
        Comment comment = commentService.addToTopicUsingGetOne(-1l, "text");
        Assertions.assertEquals("text", comment.getText());
    }

}
