package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
