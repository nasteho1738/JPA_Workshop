package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.AppUser;
import com.lexicon.jpa_workshop.Entity.Book;
import com.lexicon.jpa_workshop.Entity.BookLoan;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookLoanRepositoryTest {
    @Autowired
    private BookLoanRepository bookLoanRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    private BookLoan loanBookTest1;
    private BookLoan loanBookTest2;

    private AppUser user;

    private Book book1;
    private Book book2;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        user = new AppUser("Test","test@test.com");
        user = appUserRepository.save(user);

        // Initialize Books
        book1 = new Book();
        book1.setTitle("Book 1");

        book1 = bookRepository.save(book1);

        book2 = new Book();
        book2.setTitle("Book 2");

        book2 = bookRepository.save(book2);


        loanBookTest1 = new BookLoan();
        loanBookTest1.setBook(book1);
        loanBookTest1.setUser(user);
        loanBookTest1.setLoanDate(LocalDate.now());

        loanBookTest1 = bookLoanRepository.save(loanBookTest1);

        loanBookTest2 = new BookLoan();
        loanBookTest2.setBook(book2);
        loanBookTest2.setUser(user);
        loanBookTest2.setLoanDate(LocalDate.now());

        loanBookTest2 = bookLoanRepository.save(loanBookTest2);
    }
    @Test
    @Transactional
    public void testCreateBookLoan() {
        Book book = new Book();
        book.setTitle("New Book");
        book = bookRepository.save(book);

        BookLoan newLoan = new BookLoan();
        newLoan.setBook(book);
        newLoan.setUser(user);
        newLoan.setLoanDate(LocalDate.now());

        newLoan = bookLoanRepository.save(newLoan);

        assertNotNull(newLoan.getId());
        Optional<BookLoan> foundLoan = bookLoanRepository.findById(newLoan.getId());
        assertTrue(foundLoan.isPresent());
        assertEquals(newLoan.getId(), foundLoan.get().getId());
    }
    @Test
    @Transactional
    public void testReadBookLoan() {
        Optional<BookLoan> foundLoan = bookLoanRepository.findById(loanBookTest1.getId());
        assertTrue(foundLoan.isPresent());
        assertEquals(loanBookTest1.getId(), foundLoan.get().getId());
    }
    @Test
    @Transactional
    public void testUpdateBookLoan() {
        Optional<BookLoan> foundLoan = bookLoanRepository.findById(loanBookTest1.getId());
        assertTrue(foundLoan.isPresent());

        BookLoan loanToUpdate = foundLoan.get();
        LocalDate newLoanDate = LocalDate.now().minusDays(1);
        loanToUpdate.setLoanDate(newLoanDate);

        bookLoanRepository.save(loanToUpdate);

        Optional<BookLoan> updatedLoan = bookLoanRepository.findById(loanToUpdate.getId());
        assertTrue(updatedLoan.isPresent());
        assertEquals(newLoanDate, updatedLoan.get().getLoanDate());
    }

    @Test
    @Transactional
    public void testDeleteBookLoan() {
        bookLoanRepository.deleteById(loanBookTest1.getId());

        Optional<BookLoan> deletedLoan = bookLoanRepository.findById(loanBookTest1.getId());
        assertFalse(deletedLoan.isPresent());
    }



}
