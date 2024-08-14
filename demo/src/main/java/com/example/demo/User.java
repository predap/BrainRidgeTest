package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "UserTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    @NotBlank
    @NotEmpty
    private String username;
    @Column(name = "balance")
    private Double balance;
    @Column(name="history")
    private LinkedList<Long> history;

    public User(String name, Double initial) {
        username = name;
        balance = initial;
        history = new LinkedList<>();
    }

    public User() {
        history = new LinkedList<>();
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, balance=%f, history=%s",
                id, balance, history);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getTransactionNumber() {
        return history.size();
    }

    public List<Long> getHistory() {
        return history;
    }

    public Long getTransaction(Integer num) {
        return history.get(num);
    }

    public void addTransaction(Transaction t) {
        if (t.getReceiver() == id) {
            System.out.println("User " + username + " received transaction!");
            balance += t.getAmount();
        }
        if (t.getSender() == id) {
            System.out.println("User " + username + " sent transaction!");
            balance -= t.getAmount();
        }
        history.add(t.getId());
        System.out.println(history.size());
    }
}