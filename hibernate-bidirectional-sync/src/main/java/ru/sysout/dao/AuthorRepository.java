package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
