package ru.sysout.springdataentitygraph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.dao.PostRepository;
import ru.sysout.model.Image;
import ru.sysout.model.Post;

import java.util.*;

@SpringBootTest
class SpringDataEntitygraphApplicationTests {
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void initDb() {
		for (int i = 0; i < 5; i++) {
			Post post = new Post("topic" + i);
			Image image1 = new Image("url1_" + i);
			Image image2 = new Image("url2_" + i);
			post.addImage(image1);
			post.addImage(image2);

			Set<String> tags = new HashSet<>(Arrays.asList("red", "green", "blue", "orange", "white"));
			post.setTags(tags);
			postRepository.save(post);
		}
    }

	@Test
	@DisplayName("если использовать готовый EntityGraph, images загружаются")
	public void givenEntityGraph_whenFind_thenImagesAreEager() {
		List<Post> posts = postRepository.findByTitle( "topic1");
			}

	@Test
	@DisplayName("если создать EntityGraph динамически, тоже работает - tags загружаются")
	public void whenBuildGraphDynamically_thenTagsAreEager() {
		Optional<Post> opt = postRepository.findById( 1l);
	}

	@Test
	@DisplayName("EntityGraph работает с @Query")
	public void givenEntityGraph_whenWithQuery_thenImagesAreEager() {
		Post post = postRepository.getPostWithImages(1l);
	}

	@Test
	@DisplayName("EntityGraph работает с custom методами")
	public void givenEntityGraph_whenWithCustomMethod_thenOk() {
		List<Post> post = postRepository.findByTitleDesc("topic1");
	}

	@AfterEach
	private void clearDb(){
    	postRepository.deleteAll();
	}
}
