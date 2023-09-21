package com.setxpro.picpay.domain.transaction;

import com.setxpro.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
