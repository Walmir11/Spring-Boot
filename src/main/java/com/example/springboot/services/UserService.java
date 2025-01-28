package com.example.springboot.services;

import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Optional<UserModel> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}