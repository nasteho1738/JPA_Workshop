package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    List<AppUser> findAllByRegDateBetween(LocalDate startDate, LocalDate endDate);

    List<AppUser> findByUserDetails_Id(Long detailsId);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);

    List<AppUser> findByRegDateBetween(LocalDate startDate, LocalDate endDate);

    AppUser findByUserDetails_Id(long id);
}
