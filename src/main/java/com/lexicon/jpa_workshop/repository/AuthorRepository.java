package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    List<Author> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    List<Author> findByWrittenBooksId(Long bookId);
}
