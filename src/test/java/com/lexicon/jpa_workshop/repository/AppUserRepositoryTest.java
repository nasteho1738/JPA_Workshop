package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.AppUser;
import com.lexicon.jpa_workshop.Entity.Details;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;
    private Long detailsId;

    @Test
    @Transactional
    public void testSaveAndFindById() {
        AppUser appUser = new AppUser("Nasteho", "57253");
        AppUser savedAppUser = appUserRepository.save(appUser);
        appUser.setUserDetails();
        assertNotNull(savedAppUser);
        assertNotNull(savedAppUser.getId());

        Details details = new Details("Nasteho", "nasteho1738@icloud.com", LocalDate.now());
        Details createDetails = detailsRepository.save(details);
        assertNotNull(createDetails);
        assertNotNull(details);


        Optional<AppUser> found = appUserRepository.findByUsername("Nasteho");
        assertTrue(found.isPresent());

    }

    @Test
    @Transactional
    public void testFindByUsername() {
        AppUser appUser = new AppUser("username", "password");
        appUserRepository.save(appUser);

        Optional<AppUser> foundAppUser = appUserRepository.findByUsername("username");

        Assertions.assertNotNull(foundAppUser);
        Assertions.assertEquals("username", foundAppUser.get().getUsername());
    }

    @Test
    @Transactional
    public void testFindByRegistrationDateBetween() {

        AppUser appUser1 = new AppUser("Nate1", "749293");
        appUser1.setRegDate(LocalDate.of(2022, 1, 1));
        appUserRepository.save(appUser1);

        AppUser appUser2 = new AppUser("Nelly2", "y834o238");
        appUser2.setRegDate(LocalDate.of(2022, 1, 15));
        appUserRepository.save(appUser2);

        AppUser appUser3 = new AppUser("Nav3", "7943758");
        appUser3.setRegDate(LocalDate.of(2022, 2, 1));
        appUserRepository.save(appUser3);

        LocalDate startDate = LocalDate.of(2022, 1, 10);
        LocalDate endDate = LocalDate.of(2022, 1, 20);
        List<AppUser> foundAppUsers = appUserRepository.findByRegDateBetween(startDate, endDate);


        Assertions.assertEquals(1, foundAppUsers.size());
        Assertions.assertEquals("Nelly2", foundAppUsers.get(0).getUsername());
    }

   /* @Test
    @Transactional
    public void testFindByDetailsId() {

        AppUser appUser = new AppUser("username", "password");
        appUserRepository.save(appUser);

        Details details = new Details("Nasteho", "nasteho1738@icloud.com", LocalDate.now());
        details.setAppUser(appUser);
        detailsRepository.save(details);


        AppUser foundAppUser = appUserRepository.findByDetails_Id(details.getId());

        Assertions.assertNotNull(foundAppUser);
        Assertions.assertEquals("username", foundAppUser.getUsername());
    }

    */


}
