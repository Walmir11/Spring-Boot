package com.example.demo.services;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getOneUser(UUID id) {
        return userRepository.findById(id);
    }

    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public void deleteUser(UserModel userModel) {
        userRepository.delete(userModel);
    }

    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Optional<UserModel> getOneUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}