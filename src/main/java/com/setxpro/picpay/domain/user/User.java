package com.setxpro.picpay.domain.user;

import com.setxpro.picpay.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users") // Entity on db col reference
@Table(name="users") // Table name
@Getter // Generate setters automatic
@Setter // Generate setters automatic
@AllArgsConstructor // generate constructor with all params
@NoArgsConstructor
@EqualsAndHashCode(of="id") // primary key
public class User {
    // Field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generate autoincrement
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.document = data.document();
        this.password = data.password();
        this.balance = data.balance();
        this.userType = data.userType();
    }

}
