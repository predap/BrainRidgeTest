package com.example.demo.Controllers;

import com.example.demo.Services.UserService;
import com.example.demo.Transaction;
import com.example.demo.User;
import com.example.demo.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/create")
    public ResponseEntity createUser(@RequestBody UserDTO newUser, HttpServletRequest request) {
        try {
            User user = userService.saveUser(newUser);
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).body(user);
        } catch (ServerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/user/{userId}")
    public List<Transaction> getHistory(@PathVariable Long userId) {
        return userService.getUserHistoryById(userId);
    }
}
