package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    private long id;
    private String username;
    private double balance;

    public UserDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        balance = user.getBalance();
    }

    public UserDTO(@JsonProperty("username") String username, @JsonProperty("balance") double balance) {
        this.username = username;
        this.balance = balance;
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
}
