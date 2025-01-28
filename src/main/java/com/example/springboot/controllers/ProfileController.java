package com.example.springboot.controllers;

import com.example.springboot.dtos.ProfileDto;
import com.example.springboot.models.ProfileModel;
import com.example.springboot.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileModel> saveProfile(@RequestBody @Valid ProfileDto profileDto) {
        var profileModel = new ProfileModel();
        BeanUtils.copyProperties(profileDto, profileModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.saveProfile(profileModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProfileById(@PathVariable(value = "id") UUID id) {
        Optional<ProfileModel> profileO = profileService.getProfileById(id);
        if (profileO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(profileO.get());
    }

    @GetMapping
    public ResponseEntity<List<ProfileModel>> getAllProfiles() {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getAllProfiles());
    }
}