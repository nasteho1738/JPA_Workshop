package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private String title;
    private String isbn;
    private int maxLoanDays;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans = new HashSet<>();

    public Book(String theBook, String testBook, String s, int i, LocalDate now, LocalDate localDate) {
    }

    public Book(String title, String isbn, String s, int maxLoanDays, LocalDate now, Set<BookLoan> bookLoans) {
        this.title = title;
        this.isbn = isbn;
        this.maxLoanDays = maxLoanDays;
        this.bookLoans = bookLoans;
    }

    public Book(String title, String isbn, int maxLoanDays) {
        this.title = title;
        this.isbn = isbn;
        this.maxLoanDays = maxLoanDays;
    }

    public void addBookLoan(BookLoan bookLoan) {
        bookLoans.add(bookLoan);
        bookLoan.setBook(this);
    }
    public void removeBookLoan(BookLoan bookLoan) {
        bookLoans.remove(bookLoan);
        bookLoan.setBook(null);
    }
}
