package com.example.springboot.services;

import com.example.springboot.models.ProfileModel;
import com.example.springboot.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileModel saveProfile(ProfileModel profileModel) {
        return profileRepository.save(profileModel);
    }

    public Optional<ProfileModel> getProfileById(UUID id) {
        return profileRepository.findById(id);
    }

    public List<ProfileModel> getAllProfiles() {
        return profileRepository.findAll();
    }
}