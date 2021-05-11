package ru.sysout.springdata.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.springdata.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
    List<User> getByName(String name);
    List<User> queryByName(String name);
    List<User> readByName(String name);

    List<User> findAllByName(String name);
    List<User> findUserByName(String name);
    List<User> findUsersByName(String name);


    Optional<User> findFirstByName(String name);

    List<User> findFirst2ByName(String name);

    List<User> findDistinctUserByAccounts_NameContaining(String str);

    Optional<User> findByNameContaining(String str);

    List<User>  findByNameIsStartingWith(String str);


    List<User> findDistinctByAccounts_AmountIsGreaterThan(long n);

    List<User> findDistinctByNameOrAccounts_AmountIsGreaterThan(String name, long amount);

    List<User> findByNameIn(Iterable name);

    Page<User> findByName(String name, Pageable pageable);

    List<User> findByNameContainingOrderByNameAsc(String str);
    List<User> findByNameContaining(String str, Sort sort);

    int countAllByName(String name);
    int countDistinctUserByAccounts_NameContaining(String accountNameStr);

    int deleteByName(String name);
    void removeUserByName(String name);
}
