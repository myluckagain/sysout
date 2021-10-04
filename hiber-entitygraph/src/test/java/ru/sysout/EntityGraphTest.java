package ru.sysout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sysout.model.Image;
import ru.sysout.model.Post;

import javax.persistence.EntityGraph;
import java.util.*;

public class EntityGraphTest {

    @BeforeAll
    private static void createPosts() {
        HibernateUtil.doInHibernate(session -> {
            for (int i = 0; i < 5; i++) {
                Post post = new Post("topic" + i);
                Image image1 = new Image("url1_" + i);
                Image image2 = new Image("url2_" + i);
                post.addImage(image1);
                post.addImage(image2);

                Set<String> tags = new HashSet<>(Arrays.asList("red", "green", "blue", "orange", "white"));
                post.setTags(tags);
                session.persist(post);
            }
        });
    }

    @Test
    @DisplayName("если не использовать EntityGraph, коллекции не загружаются")
    public void givenDefaultFetchStrategy_whenFind_thenCollectionsAreLazy() {

        HibernateUtil.doInHibernate(session -> {
            Post post = session.find(Post.class, 1l);
        });
    }
    @Test
    @DisplayName("если использовать EntityGraph, загружаются images")
    public void givenEntityGraph_whenFind_thenImagesAreEager() {
        HibernateUtil.doInHibernate(session -> {
            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.fetchgraph", session.getEntityGraph("post-entity-graph"));
            Post post = session.find(Post.class, 1l, properties);

        });
    }

    @Test
    @DisplayName("если создать динамически EntityGraph, загружаются tags")
    public void givenDynamicEntityGraph_whenFind_thenTagsAreEager() {

        HibernateUtil.doInHibernate(session -> {

            Map<String, Object> properties = new HashMap<>();
            EntityGraph<Post> postGraph = session.createEntityGraph(Post.class);
            postGraph.addAttributeNodes("tags");
            properties.put("javax.persistence.fetchgraph", postGraph);
            Post post = session.find(Post.class, 1l, properties);

        });
    }


    @Test
    @DisplayName("EntityGraph c Query тоже работает")
    public void givenEntityGraph_whenQuery_thenImagesAreEager() {
        HibernateUtil.doInHibernate(session -> {
            EntityGraph entityGraph = session.getEntityGraph("post-entity-graph");
            Post post = session.createQuery("select p from Post p where p.id = :id", Post.class)
                    .setParameter("id", 1l)
                    .setHint("javax.persistence.fetchgraph", entityGraph)
                    .getSingleResult();
        });
    }

    @Test
    @DisplayName("если создать loadgraph и раскомментировать (fetch = FetchType.EAGER), загружаются tags")
    public void givenLoadGraph_whenFind_thenTagsAreEager() {

        HibernateUtil.doInHibernate(session -> {

            Map<String, Object> properties = new HashMap<>();
            EntityGraph<Post> postGraph = session.createEntityGraph(Post.class);
           // postGraph.addAttributeNodes("images");
            properties.put("javax.persistence.loadgraph", postGraph);
            Post post = session.find(Post.class, 1l, properties);

        });
    }




}

