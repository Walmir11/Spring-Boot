package com.example.springboot.controllers;

import com.example.springboot.models.ProfileModel;
import com.example.springboot.models.UserModel;
import com.example.springboot.services.ProfileService;
import com.example.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<Object> addProfileToUser(@PathVariable(value = "userId") UUID userId,
                                                   @PathVariable(value = "profileId") UUID profileId) {
        Optional<UserModel> userO = userService.getUserById(userId);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        UserModel userModel = userO.get();
        Optional<ProfileModel> profileO = profileService.getProfileById(profileId);
        if (profileO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found.");
        }
        userModel.getProfiles().add(profileO.get());
        userService.saveUser(userModel);
        return ResponseEntity.status(HttpStatus.OK).body("Profile added to user successfully.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserProfiles(@PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> userO = userService.getUserById(userId);
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        UserModel userModel = userO.get();
        Set<ProfileModel> profiles = userModel.getProfiles().stream()
                .filter(profile -> "A".equals(profile.getPuTxStatus()) && "A".equals(profile.getPerTxStatus()))
                .collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.OK).body(profiles);
    }
}