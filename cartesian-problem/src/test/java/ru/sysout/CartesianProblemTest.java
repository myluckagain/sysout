package ru.sysout;

import org.hibernate.annotations.QueryHints;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sysout.model.Image;
import ru.sysout.model.Post;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartesianProblemTest {

    @BeforeAll
    private static void createPosts() {
        HibernateUtil.doInHibernate(session -> {
            for (int i = 0; i < 5; i++) {
                Post post = new Post("topic" + i);
                Image image1 = new Image("url1_" + i);
                Image image2 = new Image("url2_" + i);
                post.addImage(image1);
                post.addImage(image2);

                Set<String> tags = Arrays.asList("red", "green", "blue", "orange", "white").stream().collect(Collectors.toSet());
                post.setTags(tags);
                session.persist(post);
            }
        });
    }

    @Test
    @DisplayName("если поставить FetchType.EAGER, то find создает большой Cartesian Product ")
    public void givenEager_whenFind_thenCartesianProblem() {
        HibernateUtil.doInHibernate(session -> {
            Post post = session.find(Post.class, 1l);
        });
    }

    @Test
    @DisplayName("если поставить FetchType.EAGER, и сделать обычный select, то получим N+1 проблему")
    public void givenEager_whenSimpleSelect_thenNPlus1Problem() {
        HibernateUtil.doInHibernate(session -> {
            List<Post> posts = session.createQuery("select p from Post p", Post.class).getResultList();
        });
    }

    @Test
    @DisplayName("если FetchType.LAZY и выполнить отдельные select для заполнения коллекций, то проблемы нет")
    public void givneLazy_whenSelectCollectionsByOne_thenOk() {
        HibernateUtil.doInHibernate(session -> {
            List<Post> posts = session
                    .createQuery(
                            "select distinct p " +
                                    "from Post p " +
                                    "left join fetch p.images " +
                                    "where p.id between :minId and :maxId ", Post.class)
                    .setParameter("minId", 1L)
                    .setParameter("maxId", 1L)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .getResultList();

            posts = session
                    .createQuery(
                            "select distinct p " +
                                    "from Post p " +
                                    "left join fetch p.tags t " +
                                    "where p in :posts ", Post.class)
                    .setParameter("posts", posts)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .getResultList();

        });
    }

}
