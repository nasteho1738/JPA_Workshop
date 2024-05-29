package com.lexicon.jpa_workshop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "JPA_Workshop")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
public class AppUser {
      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      private long id;

      @Column(nullable = false, unique = true)
      @Setter
      private String username;
      @Column(nullable = false)
      private String password;
      @Column(name = "reg_Date")
      private LocalDate regDate;
      @OneToOne
      @JoinColumn(name = "Details_id", referencedColumnName = "id")
      private Details userDetails;





}
