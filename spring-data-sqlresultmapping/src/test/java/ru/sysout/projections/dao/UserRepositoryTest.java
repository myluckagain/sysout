package ru.sysout.projections.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.projections.model.User;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindUsersCommon() {
        List<Object[]> users = userRepository.findUsersCommon();
        for (Object[] user : users) {
            long id = ((BigInteger) user[0]).longValue();
            String email = (String) user[1];
            String nickname = (String) user[2];
            String password = (String) user[3];
            String role = (String) user[4];
            boolean locked = (Boolean) user[5];
            User user1 = new User(id, email, nickname, password, role, locked);
        }
        assertEquals(2, users.size());

    }

    @Test
    public void shouldFindUsers() {
        List<User> users = userRepository.findUsersDefaultMapping();
        for (User user : users) {
            System.out.println(user.getEmail());
        }
        assertEquals(2, users.size());
    }

    @Test
    public void shouldFindUsersWithPostCount() {
        List<Tuple> users = userRepository.findUsersWithPostCount();
        for (Tuple tuple : users) {
            User user = tuple.get(0, User.class);
            int count = tuple.get(1, Integer.class);
            assertNotNull(user);
            assertTrue(count>=0);
        }
        assertEquals(2, users.size());
    }


}
