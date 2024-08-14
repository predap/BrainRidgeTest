package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sender")
    private Long sender;
    @Column(name = "receiver")
    private Long receiver;
    @Column(name = "amount")
    private Double amount;

    public Transaction(Long sender, Long receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Transaction() {}

    @Override
    public String toString() {
        return String.format("Transaction[id=%d, sender=%d, receiver=%d, amount=%f]",
                id, sender, receiver, amount);
    }

    public Long getId() {
        return id;
    }

    public Long getSender() {
        return sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public Double getAmount() {
        return amount;
    }
}
