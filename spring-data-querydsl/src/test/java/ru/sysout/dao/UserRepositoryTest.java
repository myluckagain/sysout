package ru.sysout.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.model.User;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    CustomUserRepository userRepository;


    @Test
    public void shouldFindUsersWithJoin() {
        List<User> users = userRepository.findWithJoin("S%");
        Assertions.assertEquals(2, users.size());
    }

}
