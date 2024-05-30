package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUserDetails() {
    }
}
