package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
    Optional<Details> findByEmail(String email);
    List<Details> findByNameContaining(String name);
    List<Details> findByNameContainingIgnoreCase(String name);

}
