package com.example.demo.Controllers;

import com.example.demo.Services.TransactionService;
import com.example.demo.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transaction/create")
    public ResponseEntity createTransaction(@RequestBody Transaction newTransaction, HttpServletRequest request) {
        try {
            Transaction t = transactionService.saveTransaction(newTransaction);
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(t.getId())
                    .toUri();
            return ResponseEntity.created(location).body(t);
        } catch (ServerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
