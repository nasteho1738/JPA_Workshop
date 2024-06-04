package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);

    List<Book> findByBookLoans_ReturnedIsFalse();

    List<Book> findByBookLoans_DueDateBeforeAndBookLoans_ReturnedIsFalse(LocalDate date);

    List<Book> findByBookLoans_LoanDateBetween(LocalDate startDate, LocalDate endDate);
}
