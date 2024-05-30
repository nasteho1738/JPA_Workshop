package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    List<AppUser> findAllByRegDateBetween(LocalDate startDate, LocalDate endDate);

    List<AppUser> findByUserDetails_Id(Long detailsId);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);

    List<AppUser> findByRegDateBetween(LocalDate startDate, LocalDate endDate);

    AppUser findByDetails_Id(long id);
}
