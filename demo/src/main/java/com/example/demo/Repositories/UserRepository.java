package com.example.demo.Repositories;

import com.example.demo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);

    Optional<User> findById(long id);
}
