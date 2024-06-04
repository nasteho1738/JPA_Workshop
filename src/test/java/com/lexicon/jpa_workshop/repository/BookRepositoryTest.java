package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.Book;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        // Initialize Books
        book1 = new Book();
        book1.setTitle("Book 1");
        // Set other properties of Book 1 as required
        book1 = bookRepository.save(book1);

        book2 = new Book();
        book2.setTitle("Book 2");
        // Set other properties of Book 2 as required
        book2 = bookRepository.save(book2);
    }

    @Transactional
    @Test
    public void testCreateBook() {
        Book book = new Book("The book table", "Test Book", "A. Lister", 14, LocalDate.now(), LocalDate.now().plusDays(10));
        Book savedBook = bookRepository.save(book);

        Book foundBook = bookRepository.findById(book.getId()).get();

        assertNotNull(savedBook);
        assertEquals(savedBook.getTitle(), foundBook.getTitle());

    }

    @Test
    @Transactional
     public void testFindBookByIsbn() {

        Book book = new Book("The book 1", "1234", 14);
        bookRepository.save(book);
        String isbn = "1234";
        Optional<Book> foundBook = Optional.ofNullable(bookRepository.findByIsbnIgnoreCase(isbn));

        assertTrue(foundBook.isPresent());
        assertEquals(isbn, foundBook.get().getIsbn());
    }
    @Test
    @Transactional
    public void testUpdateBook() {
        Optional<Book> foundBook = bookRepository.findById(book1.getId());
        assertTrue(foundBook.isPresent());

        Book bookToUpdate = foundBook.get();
        String newTitle = "Updated Book Title";
        bookToUpdate.setTitle(newTitle);

        bookRepository.save(bookToUpdate);

        Optional<Book> updatedBook = bookRepository.findById(bookToUpdate.getId());
        assertTrue(updatedBook.isPresent());
        assertEquals(newTitle, updatedBook.get().getTitle());
    }
    @Test
    @Transactional
    public void testDeleteBook() {
        bookRepository.deleteById(book1.getId());

        Optional<Book> deletedBook = bookRepository.findById(book1.getId());
        assertFalse(deletedBook.isPresent());
    }


}
