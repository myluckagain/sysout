package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
