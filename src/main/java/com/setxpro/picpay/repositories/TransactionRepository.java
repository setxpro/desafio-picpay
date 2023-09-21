package com.setxpro.picpay.repositories;

import com.setxpro.picpay.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
