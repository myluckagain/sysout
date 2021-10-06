package ru.sysout.dao;

import ru.sysout.model.User;

import java.util.List;

public interface CustomUserRepository {
    List<User> findWithJoin(String title);
}
