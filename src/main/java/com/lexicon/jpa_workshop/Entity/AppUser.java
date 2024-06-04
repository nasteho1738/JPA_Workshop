package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> bookLoans;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserDetails() {
    }
}
