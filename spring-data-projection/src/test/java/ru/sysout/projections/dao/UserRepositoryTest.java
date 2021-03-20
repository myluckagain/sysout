package ru.sysout.projections.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sysout.projections.dto.UserDto;
import ru.sysout.projections.dto.UserView;
import ru.sysout.projections.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindOpenProjection() {
        UserView userView = userRepository.findByNickname("admin");

        assertEquals("admin@example.com password", userView.getInfo());
    }

    @Test
    public void shouldFindClassProjection() {
        List<UserDto> userDtos = userRepository.findByEmail("admin@example.com");
        UserDto userDto = userDtos.get(0);
        assertEquals("admin", userDto.getNickname());
    }

    @Test
    public void shouldFindDynamicProjection() {
        UserDto userDto = userRepository.findByNickname("admin", UserDto.class);
        assertEquals("admin", userDto.getNickname());

        User user = userRepository.findByNickname("admin", User.class);
        assertEquals("admin", user.getNickname());

        UserView userView = userRepository.findByNickname("admin", UserView.class);
        assertEquals("admin", userView.getNickname());
    }
}
