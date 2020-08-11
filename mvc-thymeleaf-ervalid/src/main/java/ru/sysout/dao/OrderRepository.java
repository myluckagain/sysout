package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
}
