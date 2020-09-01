package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.UserDetailsRepository;
import ru.sysout.dao.UserRepository;
import ru.sysout.model.User;
import ru.sysout.model.UserDetails;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    @DisplayName("по id User можно найти UserDetails")
    public void whenFindUser_ThenLazy() {
        Optional<User> optionalUser = userRepository.findById(1l);
        Assertions.assertTrue(optionalUser.isPresent());

        Optional<UserDetails> optionalUserDetails=userDetailsRepository.findById(1l);
        Assertions.assertTrue(optionalUserDetails.isPresent());
    }
}
