package ru.sysout.springdatajdbc.dao;

import ru.sysout.springdatajdbc.model.User;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findUserByHisName(String str);
}
