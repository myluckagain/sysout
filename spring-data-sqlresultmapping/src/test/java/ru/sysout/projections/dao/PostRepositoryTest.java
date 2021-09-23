package ru.sysout.projections.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.projections.dto.PostDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;


    @Test
    public void shouldFindPostDtos() {
        List<PostDto> list = postRepository.findPostDtos();
        assertEquals(3, list.size());
    }

    @Test
    public void shouldFindDtos() {
        List<PostDto> list = postRepository.findDtos();
        assertEquals(3, list.size());
    }

}
