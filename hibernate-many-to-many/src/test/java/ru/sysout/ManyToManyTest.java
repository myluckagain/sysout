package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import ru.sysout.dao.AuthorRepository;
import ru.sysout.dao.BookRepository;
import ru.sysout.model.Author;
import ru.sysout.model.Book;

@DataJpaTest
@Commit
public class ManyToManyTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    @BeforeEach
    public void booksShouldBeAdded() {
        Author author1 = new Author("a1");

        Book b1 = new Book("b1");
        Book b2 = new Book("b2");
        author1.addBook(b1);
        author1.addBook(b2);

        authorRepository.save(author1);

        Author author2 = new Author("a2");
        author2.addBook(b1);
        authorRepository.save(author2);

        Assertions.assertEquals(2, authorRepository.count());
        Assertions.assertEquals(2, bookRepository.count());
    }

    @Test
    @DisplayName("при удалении автора из книги с Set должен выполняться один delete-оператор")
    public void whenDeleteAuthorFromBook_thenOneDeleteStatement() {
        Author author = authorRepository.findByName("a1");
        Book book = bookRepository.findByName("b1");
        author.removeBook(book);
    }
}
