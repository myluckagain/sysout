package ru.sysout.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import ru.sysout.dao.UserRepository;
import ru.sysout.model.User;

import java.util.List;

@DataJpaTest
@Import(UserService.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init(){
        userService.addUser("Jane1", "111-111");
        userService.addUser("Jane2", null);
    }
    @Test
    void shouldGetUsers(){
     List<User>  users=userRepository.findAll();
        Assertions.assertEquals(2, users.size());
    }
}
