package ru.sysout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}
