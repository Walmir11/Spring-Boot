package com.example.demo;

import com.example.demo.models.User;
import com.example.demo.models.Band;
import com.example.demo.models.Song;
import com.example.demo.services.UserService;
import com.example.demo.services.BandService;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BandService bandService;

    @Autowired
    private SongService songService;

    @Override
    public void run(String... args) throws Exception {
        // Adicionando usuários
        User user1 = new User();
        user1.setUsername("testuser1");
        user1.setPassword("password1");
        user1.setRole("USER");
        userService.save(user1);

        User user2 = new User();
        user2.setUsername("testuser2");
        user2.setPassword("password2");
        user2.setRole("USER");
        userService.save(user2);

        // Adicionando bandas
        Band band1 = new Band();
        band1.setName("Test Band 1");
        bandService.save(band1);

        Band band2 = new Band();
        band2.setName("Test Band 2");
        bandService.save(band2);

        // Adicionando músicas
        Song song1 = new Song();
        song1.setTitle("Song 1");
        song1.setPdfPath("/path/to/song1.pdf");
        song1.setActive(true);
        song1.setBand(band1);
        songService.save(song1);

        Song song2 = new Song();
        song2.setTitle("Song 2");
        song2.setPdfPath("/path/to/song2.pdf");
        song2.setActive(true);
        song2.setBand(band2);
        songService.save(song2);

        System.out.println("Dados iniciais adicionados.");
    }
}
