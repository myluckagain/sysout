package ru.sysout.springdatajdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.sysout.springdatajdbc.dao.AccountRepository;
import ru.sysout.springdatajdbc.dao.UserRepository;
import ru.sysout.springdatajdbc.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private AccountRepository dao;
    @Autowired
    private UserRepository userDao;

    @Test
    void t1() {
        List<User> users = userDao.findByName("John");
        //   List<User> users=userDao.getByName("John");
        //  List<User> users=userDao.queryByName("John");
        // List<User> users = userDao.readByName("John");
        //   List<User> users=userDao.findAllByName("John");
        //   List<User> users=userDao.findUsersByName("John");
        //  List<User> users=userDao.findUserByName("John");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void t2() {

        Optional<User> optionalUser = userDao.findFirstByName("John");

        Assertions.assertFalse(optionalUser.isEmpty());
    }

    @Test
    void t3() {

        List<User> users = userDao.findFirst2ByName("John");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void t4() {
        List<User> users = userDao.findDistinctUserByAccounts_NameContaining("ac2");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void t5() {
        List<User> users = userDao.findByNameIsStartingWith("J");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void t6() {

        List<User> users = userDao.findDistinctByAccounts_AmountIsGreaterThan(15);
        Assertions.assertEquals(2, users.size());

    }

    @Test
    void t7() {

        List<User> users = userDao.findDistinctByNameOrAccounts_AmountIsGreaterThan("Petr", 15);
        Assertions.assertEquals(3, users.size());

    }

    @Test
    void t8() {

        List<User> users = userDao.findByNameIn(Arrays.asList("Ivan", "Petr"));
        Assertions.assertEquals(2, users.size());

    }


    @Test
    void t9() {
        Assertions.assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> {
                    Optional<User> opt = userDao.findByNameContaining("J");
                });


    }


    @Test
    void t10() {
        Page<User> userPage = userDao.findByName("John", PageRequest.of(0, 2));
        Assertions.assertEquals(2, userPage.getTotalElements());
    }


    @Test
    void t11() {
        List<User> users = userDao.findByNameContainingOrderByNameAsc("e");
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("Artem", users.get(0).getName());
    }

    @Test
    void t12() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        List<User> users = userDao.findByNameContaining("e", sort);
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("Artem", users.get(0).getName());
    }

    @Test
    void t13() {
        int count = userDao.countAllByName("John");
        Assertions.assertEquals(2, count);
    }


    @Test
    void t14() {

        int count = userDao.countDistinctUserByAccounts_NameContaining("ac");
        Assertions.assertEquals(2, count);
    }

    @Test
    void t15() {
        int n = userDao.deleteByName("Petr");
        Assertions.assertEquals(1, n);
    }

    @Test
    void t16() {
        userDao.removeUserByName("Petr");
        Assertions.assertEquals(4, userDao.count());
    }

}
