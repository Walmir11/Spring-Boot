package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userO = userService.getOneUser(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userO.get());
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userO = userService.getOneUser(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.deleteUser(userO.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody UserModel userModel) {
        Optional<UserModel> userO = userService.getOneUser(id);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userModel.setIdUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userModel));
    }
}