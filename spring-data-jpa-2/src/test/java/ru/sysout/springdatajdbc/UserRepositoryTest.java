package ru.sysout.springdatajdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.springdatajdbc.dao.UserRepository;
import ru.sysout.springdatajdbc.model.User;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository dao;


    @Test
    void t1() {
        Optional<User> user= dao.findUserByHisName("John");
        Assertions.assertTrue(user.isPresent());

        user= dao.findUserByHisName("J");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    void t2() {
        Optional<User> user= dao.findFirstByName("John");
        Assertions.assertTrue(user.isPresent());

        user= dao.findFirstByName("J");
        Assertions.assertTrue(user.isEmpty());
    }




}
