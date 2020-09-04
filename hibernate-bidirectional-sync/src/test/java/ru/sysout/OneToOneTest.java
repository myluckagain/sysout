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

@DataJpaTest
class OneToOneTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @Test
    @DisplayName("добавление User с UserDetails")
    public void userShouldBeAdded() {
        User user = new User();
        user.setName("Vasya");
        UserDetails details = new UserDetails();
        details.setPhone("345345");
        user.setDetails(details);
        userRepository.save(user);

        Assertions.assertEquals(4, userRepository.count());
        Assertions.assertEquals(4, userDetailsRepository.count());
    }

    @Test
    @DisplayName("отделение UserDetails от User")
    public void userDetailsShouldBeRemoved() {

        User user = userRepository.findByName("Petr");
        user.setDetails(null);
        userRepository.save(user);

        Assertions.assertEquals(3, userRepository.count());
        Assertions.assertEquals(2, userDetailsRepository.count());
    }

}
