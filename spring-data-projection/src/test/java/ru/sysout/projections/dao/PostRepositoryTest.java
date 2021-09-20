package ru.sysout.projections.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.projections.dto.PostView;
import ru.sysout.projections.dto1.PostView1;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldFindInterfaceProjection() {
        List<PostView> list = postRepository.findByTitle("Super Post3");

        List expected = List.of("Super Post3");

        List actual = list.stream().map(el -> el.getTitle()).collect(Collectors.toList());

        assertLinesMatch(expected, actual);

    }


    @Test
    public void shouldFindInterfaceProjection1() {
        List<PostView> list = postRepository.findByTitleJoin("Super Post3");

        List expected = List.of("Super Post3");

        List actual = list.stream().map(el -> el.getTitle()).collect(Collectors.toList());

        assertLinesMatch(expected, actual);
        assertNotNull(list.get(0).getUser());

    }


    @Test
    public void shouldFindInterfaceProjection2() {
        List<PostView1> list = postRepository.findByTitleNativeJoin("Super Post3");

        List expected = List.of("Super Post3");

        List actual = list.stream().map(el -> el.getTitle()).collect(Collectors.toList());

        assertLinesMatch(expected, actual);
        assertNotNull(list.get(0).getUser());

    }
}
