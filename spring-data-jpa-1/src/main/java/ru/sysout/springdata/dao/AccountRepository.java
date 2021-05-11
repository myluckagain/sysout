package ru.sysout.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.springdata.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    int countByUser_IdIn(List ids);

}
