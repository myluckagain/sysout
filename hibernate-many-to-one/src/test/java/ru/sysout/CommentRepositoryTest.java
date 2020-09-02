package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.CommentRepository;
import ru.sysout.model.Comment;

import java.util.Optional;

@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("если метод FindById, то два select")
    public void givenMethod_whenFetchCommentWithTopic_thenOneSelect(){
        Optional<Comment> comment=commentRepository.findById(-4l);
        Assertions.assertTrue(comment.isPresent());
        Assertions.assertEquals("title1", comment.get().getTopic().getTitle());
    }

    @Test
    @DisplayName("если метод findWithJoinFetch, то один select")
    public void givenQuery_whenFetchCommentWithTopic_thenTwoSelects(){
        Optional<Comment> comment=commentRepository.findWithJoinFetch(-4l);
        Assertions.assertTrue(comment.isPresent());
        Assertions.assertEquals("title1", comment.get().getTopic().getTitle());
    }
}
