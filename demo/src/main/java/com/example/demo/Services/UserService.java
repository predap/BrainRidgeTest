package com.example.demo.Services;

import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Transaction;
import com.example.demo.User;
import com.example.demo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return convertToDTO(user);
    }

    public List<Transaction> getUserHistoryById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Long> hist = user.getHistory();
        System.out.println(hist.size());
        List<Transaction> ret = new LinkedList<>();
        for (Long l: hist) {
            System.out.println(l);
            ret.add(transactionRepository.findById(l).orElse(null));
        }
        return ret;
    }

    public User saveUser(UserDTO userDTO) throws ServerException {
        if (userDTO.getBalance() < 0.0) {
            throw new ServerException("Error: Account balance is negative");
        } else if (userDTO.getUsername().isEmpty()) {
            throw new ServerException("Error: No username supplied!");
        }
        User user = convertToEntity(userDTO);
        userRepository.save(user);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user);
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getBalance());
    }
}
