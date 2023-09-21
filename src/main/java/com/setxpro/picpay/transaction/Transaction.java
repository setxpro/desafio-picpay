package com.setxpro.picpay.transaction;

import com.setxpro.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne // Um usuário pode ter várias pode ter várias transações, mas uma transação só pode ter um usuário.
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne // Um usuário pode ter várias pode ter várias transações, mas uma transação só pode ter um usuário.
    @JoinColumn(name="receiver_id")
    private User receiver;

    private LocalDateTime timestamp;
}
