package ru.sysout;

import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sysout.model.Comment;
import ru.sysout.model.Topic;

import java.util.List;

public class NPlus1Test {

    @BeforeAll
    private static void createTopics() {
        HibernateUtil.doInHibernate(session -> {
            for (int i = 0; i < 5; i++) {
                Topic topic = new Topic("topic" + i);
                Comment comment = new Comment("comment" + i);
                comment.setTopic(topic);
                session.persist(comment);
                session.persist(topic);
            }
        });
    }

    @Test
    @DisplayName("если fetch = FetchType.EAGER, то получаем в консоли N+1 select: 5+1")
    public void whenEager_thenNplus1Problem() {
        HibernateUtil.doInHibernate(session -> {
            Query<Comment> query = session.createQuery("select c from Comment c");

            List<Comment> comments = query.getResultList();
            Assertions.assertEquals(5, comments.size());
            comments.forEach(comment -> System.out.println(comment.getText() + " " + comment.getTopic().getTitle()));

        });
    }

    @Test
    @DisplayName("если пофиксить проблему с помощью  join fetch, то получаем в консоли один select")
    public void whenJoinFetch_thenNoProblem() {
        HibernateUtil.doInHibernate(session -> {
            Query<Comment> query = session.createQuery("select c from Comment c join fetch c.topic t", Comment.class);

            List<Comment> comments = query.getResultList();
            Assertions.assertEquals(5, comments.size());
            comments.forEach(comment -> System.out.println(comment.getText() + " " + comment.getTopic().getTitle()));
        });
    }
}
