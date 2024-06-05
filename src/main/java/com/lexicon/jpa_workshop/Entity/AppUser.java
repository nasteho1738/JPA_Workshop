package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "reg_Date")
    private LocalDate regDate;
    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans = new HashSet<>();

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserDetails() {
    }
}
