package ru.sysout.projections.dao;

import ru.sysout.projections.model.User;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepositoryCustom  {

    List<Object[]> findUsersCommon();
    List<User> findUsersDefaultMapping();
    List<Tuple> findUsersWithPostCount();



}
