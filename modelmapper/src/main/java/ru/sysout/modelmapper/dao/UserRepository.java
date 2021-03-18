package ru.sysout.modelmapper.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.modelmapper.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getById(long id);
}
