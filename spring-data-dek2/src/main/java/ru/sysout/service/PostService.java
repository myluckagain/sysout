package ru.sysout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.PostRepository;
import ru.sysout.model.Post;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Transactional
    public List<Post> getPosts(){
        List<Post> posts = postRepository.findWithImages(1l, 1l);
        posts=postRepository.findWithTags(posts);
        return posts;
    }
}
