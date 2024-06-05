package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")

    )
    private Set<Book> writtenBooks = new HashSet<>();



    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void addBook(Book book) {
        if (isValidBookToAdd(book)) {
            boolean add = this.writtenBooks.add(book);
            book.getTitle();
        }
    }

    public void removeBook(Book book) {
        if (isValidBookToRemove(book)) {
            this.writtenBooks.remove(book);
            book.getTitle();
        }
    }

    private boolean isValidBookToAdd(Book book) {
        return book != null && !this.writtenBooks.contains(book);
    }

    private boolean isValidBookToRemove(Book book) {
        return book != null && this.writtenBooks.contains(book);
    }
}
