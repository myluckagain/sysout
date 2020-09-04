package ru.sysout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sysout.dao.AuthorRepository;
import ru.sysout.dao.BookRepository;
import ru.sysout.model.Author;
import ru.sysout.model.Book;

@DataJpaTest
public class ManyToManyTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    @Test
    @DisplayName("добавление автора c книгами")
    public void booksShouldBeAdded() {

        Author author = new Author("a1");
        author.addBook(new Book("b1"));
        author.addBook(new Book("b2"));
        authorRepository.save(author);

        Assertions.assertEquals(1, authorRepository.count());
        Assertions.assertEquals(2, bookRepository.count());
    }

    @Test
    @DisplayName("отсоединение книги от автора")
    public void booksShouldBeDeleted() {
        Author author = new Author("a1");
        author.addBook(new Book("b1"));
        author.addBook(new Book("b2"));
        authorRepository.save(author);

        Book book = bookRepository.findByName("b1");
        author.removeBook(book);
        bookRepository.delete(book);


        Assertions.assertEquals(1, authorRepository.count());
        Assertions.assertEquals(1, bookRepository.count());
    }
}
