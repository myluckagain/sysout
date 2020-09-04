package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
