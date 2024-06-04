package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "appUser_id")
    private AppUser borrower;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    public BookLoan(AppUser borrower, LocalDate loanDate, LocalDate dueDate, Book book) {

    }

    public void setUser(AppUser user) {
    }
}
