package com.example.springboot;

import com.example.springboot.models.ProfileModel;
import com.example.springboot.models.UserModel;
import com.example.springboot.services.ProfileService;
import com.example.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Cria perfis
        createProfileIfNotExists("Admin", "A");
        createProfileIfNotExists("User", "A");
        createProfileIfNotExists("Guest", "A");

        // Cria e associa usuários
        createUserIfNotExists("john.doe", "password123", "Admin", "User");
        createUserIfNotExists("jane.smith", "password456", "User");
        createUserIfNotExists("guest.user", "password789", "Guest");

        // Exibe todos os usuários e seus perfis
        userService.getAllUsers().forEach(user -> {
            System.out.println("Usuário: " + user.getUsername());
            System.out.print("Perfis: ");
            user.getProfiles().forEach(profile -> System.out.print(profile.getName() + " "));
            System.out.println();
        });
    }

    private void createProfileIfNotExists(String name, String status) {
        Optional<ProfileModel> existingProfile = profileService.getAllProfiles().stream()
                .filter(profile -> profile.getName().equals(name))
                .findFirst();
        if (existingProfile.isEmpty()) {
            ProfileModel profile = new ProfileModel();
            profile.setName(name);
            profile.setStatus(status);
            profileService.saveProfile(profile);
        } else {
            System.out.println("Perfil '" + name + "' já existe.");
        }
    }

    private void createUserIfNotExists(String username, String password, String... profileNames) {
        boolean passwordInUse = userService.getAllUsers().stream()
                .anyMatch(user -> user.getPassword().equals(password));
        if (passwordInUse) {
            System.out.println("A senha já está em uso por outro usuário.");
            return;
        }

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);

        Set<ProfileModel> profileSet = new HashSet<>();
        for (String profileName : profileNames) {
            Optional<ProfileModel> existingProfile = profileService.getAllProfiles().stream()
                    .filter(profile -> profile.getName().equals(profileName))
                    .findFirst();
            existingProfile.ifPresent(profileSet::add);
        }
        user.setProfiles(profileSet);

        userService.saveUser(user);
    }
}