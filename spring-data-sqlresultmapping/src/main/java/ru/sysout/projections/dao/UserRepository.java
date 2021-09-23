package ru.sysout.projections.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.projections.model.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
