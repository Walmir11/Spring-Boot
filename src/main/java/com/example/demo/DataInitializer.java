package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BandService bandService;

    @Autowired
    private SongService songService;

    @Autowired
    private RepertoryService repertoryService;

    @Override
    public void run(String... args) throws Exception {
        // Adicionando usuários
        createUserIfNotExists("principal_musician", "password1", "USER");
        createUserIfNotExists("band_member1", "password2", "USER");
        createUserIfNotExists("band_member2", "password3", "USER");

        // Adicionando banda
        Band band1 = new Band();
        band1.setName("Test Band 1");
        Optional<User> principalMusician = userService.findByUsername("principal_musician");
        principalMusician.ifPresent(band1::setLeader);
        bandService.save(band1);

        // Adicionando membros à banda
        Optional<User> bandMember1 = userService.findByUsername("band_member1");
        Optional<User> bandMember2 = userService.findByUsername("band_member2");
        bandMember1.ifPresent(band1::addMember);
        bandMember2.ifPresent(band1::addMember);
        bandService.save(band1);

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
        song2.setBand(band1);
        songService.save(song2);

        // Criando repertório e adicionando músicas
        Repertory repertory = new Repertory();
        repertory.setNome("Show Repertory");
        repertory.setBanda(band1);
        Set<Song> musicas = new HashSet<>();
        musicas.add(song1);
        musicas.add(song2);
        repertory.setMusicas(musicas);
        repertoryService.save(repertory);

        // Simulando a remoção de uma música do repertório
        repertory.getMusicas().remove(song2);
        repertoryService.save(repertory);

        // Re-inserindo a música no repertório
        repertory.getMusicas().add(song2);
        repertoryService.save(repertory);

        System.out.println("Dados iniciais adicionados e operações simuladas.");
    }

    private void createUserIfNotExists(String username, String password, String role) {
        Optional<User> existingUser = userService.findByUsername(username);
        if (!existingUser.isPresent()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            userService.save(user);
        }
    }
}
