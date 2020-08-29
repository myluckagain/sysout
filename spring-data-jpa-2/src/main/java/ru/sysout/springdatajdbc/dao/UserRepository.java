package ru.sysout.springdatajdbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.springdatajdbc.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    // Optional<User> findByName(String name);  //1
    // User findByName(String name); //2
    Optional<User> findFirstByName(String name); //3

}
