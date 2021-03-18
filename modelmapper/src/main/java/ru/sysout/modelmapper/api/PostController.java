package ru.sysout.modelmapper.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.modelmapper.MapperUtil;
import ru.sysout.modelmapper.dao.PostRepository;
import ru.sysout.modelmapper.dto.PostDto;
import ru.sysout.modelmapper.dto.UserDto;
import ru.sysout.modelmapper.model.Post;
import ru.sysout.modelmapper.model.User;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired

    private ModelMapper modelMapper;

    @GetMapping("/post")
    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        return MapperUtil.convertList(posts, this::convertToPostDto);
    }


    private PostDto convertToPostDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setUserDto(convertToUserDto(post.getUser()));
        return postDto;
    }

    private UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}