package ru.sysout.projections.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.projections.dto.UserDto;
import ru.sysout.projections.dto.UserView;
import ru.sysout.projections.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    UserView findByNickname(String nickname);

    List<UserDto> findByEmail(String email);

    <T> T findByNickname(String nickname, Class<T> type);
}
