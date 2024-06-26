package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, unique = true)
    private String email;
    @Column (nullable = false)
    private String name;
    @Column (name = "birth_day")
    private LocalDate birthDay;

    public Details(String email, String name, LocalDate birthDay) {
        this.email = email;
        this.name = name;
        this.birthDay = birthDay;
    }

    public void setAppUser(AppUser appUser) {
    }

}
