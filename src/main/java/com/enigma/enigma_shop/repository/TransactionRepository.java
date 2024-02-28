package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
