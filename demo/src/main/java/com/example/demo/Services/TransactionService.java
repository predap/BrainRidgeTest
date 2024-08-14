package com.example.demo.Services;

import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Transaction;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction t) throws ServerException {
        Optional<User> optReceiver = userRepository.findById(t.getReceiver());
        Optional<User> optSender = userRepository.findById(t.getSender());
        if (optReceiver.isEmpty()) {
            throw new ServerException("Error: Invalid receiver!");
        }
        User receiver = optReceiver.get();
        if (optSender.isEmpty()) {
            throw new ServerException("Error: Invalid sender!");
        }
        User sender = optSender.get();
        if (t.getAmount() < 0.0) {
            throw new ServerException("Error: Transfer amount must not be negative!");
        }
        if (sender.getBalance() < t.getAmount()) {
            throw new ServerException("Error: " + sender.getUsername() + " does not have enough funds in their account to cover the transfer cost!");
        }
        transactionRepository.save(t);
        receiver.addTransaction(t);
        userRepository.save(receiver);
        sender.addTransaction(t);
        userRepository.save(sender);
        return t;
    }
}
