package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.Author;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;


    @Test
    @Transactional
    public void testFindByFirstName() {
        Author author = new Author();
        author.setFirstName("Chris");
        author.setLastName("Brown");
        authorRepository.save(author);

        List<Author> foundAuthors = authorRepository.findByFirstName("Chris");
        assertNotNull(foundAuthors);
        assertFalse(foundAuthors.isEmpty());
    }

}
