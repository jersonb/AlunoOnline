package com.alunoonline.api.services;

import com.alunoonline.api.models.User;
import com.alunoonline.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean create(User user) {
        var userExists = repository.findByEmail(user.getEmail());
        if (userExists) {
            return false;
        }
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        repository.save(user);
        return true;
    }

    public String login(User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        var userExists = repository.findByLoginAndPassword(user.getEmail(), user.getPassword());
        if (userExists) {
            return "JWT";
        }
        return null;
    }
}
