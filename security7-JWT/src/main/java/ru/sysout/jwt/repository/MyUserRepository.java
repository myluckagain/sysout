package ru.sysout.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.jwt.model.MyUser;


@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);
}
