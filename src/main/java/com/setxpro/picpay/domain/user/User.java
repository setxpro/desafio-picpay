package com.setxpro.picpay.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="users") // Entity on db col reference
@Table(name="users") // Table name
@Getter // Generate setters automatic
@Setter // Generate setters automatic
@AllArgsConstructor // generate constructor with all params
@EqualsAndHashCode(of="id") // primary key
public class User {

    // Field

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate autoincrement
    private Long id;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

//    public User() {
//
//    }
}
