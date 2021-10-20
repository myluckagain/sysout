package ru.sysout.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sysout.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Modifying
    @Query("insert into address (id, name) values (:id, :name)")
    void addAddress(long id, String name);
}
