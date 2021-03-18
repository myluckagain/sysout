package ru.sysout.modelmapper.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sysout.modelmapper.MapperUtil;
import ru.sysout.modelmapper.dao.UserRepository;
import ru.sysout.modelmapper.model.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/user")
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return MapperUtil.convertList(users,this::convertToNoPassUser);
    }


    private User convertToNoPassUser(User user) {
        modelMapper.typeMap(User.class, User.class).addMappings(mapper -> mapper.skip(User::setPassword));
        return modelMapper.map(user, User.class);
    }

}
