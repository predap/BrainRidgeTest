package com.example.demo.Repositories;

import com.example.demo.Transaction;
import com.example.demo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction save(Transaction t);

    Optional<Transaction> findById(long id);
}
