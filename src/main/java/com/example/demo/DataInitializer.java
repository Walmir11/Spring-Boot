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
        createUserIfNotExists("freddie_mercury", "password1", "LEADER");
        createUserIfNotExists("brian_may", "password2", "USER");
        createUserIfNotExists("roger_taylor", "password3", "USER");
        createUserIfNotExists("john_deacon", "password4", "USER");

        createUserIfNotExists("mick_jagger", "password5", "LEADER");
        createUserIfNotExists("keith_richards", "password6", "USER");
        createUserIfNotExists("charlie_watts", "password7", "USER");
        createUserIfNotExists("ronnie_wood", "password8", "USER");

        // Adicionando primeira banda (Queen)
        Band queen = new Band();
        queen.setName("Queen");
        Optional<User> freddieMercury = userService.findByUsername("freddie_mercury");
        freddieMercury.ifPresent(queen::setLeader);
        bandService.save(queen);

        // Adicionando membros à banda Queen
        Optional<User> brianMay = userService.findByUsername("brian_may");
        Optional<User> rogerTaylor = userService.findByUsername("roger_taylor");
        Optional<User> johnDeacon = userService.findByUsername("john_deacon");
        brianMay.ifPresent(queen::addMember);
        rogerTaylor.ifPresent(queen::addMember);
        johnDeacon.ifPresent(queen::addMember);
        bandService.save(queen);

        // Adicionando segunda banda (The Rolling Stones)
        Band rollingStones = new Band();
        rollingStones.setName("The Rolling Stones");
        Optional<User> mickJagger = userService.findByUsername("mick_jagger");
        mickJagger.ifPresent(rollingStones::setLeader);
        bandService.save(rollingStones);

        // Adicionando membros à banda The Rolling Stones
        Optional<User> keithRichards = userService.findByUsername("keith_richards");
        Optional<User> charlieWatts = userService.findByUsername("charlie_watts");
        Optional<User> ronnieWood = userService.findByUsername("ronnie_wood");
        keithRichards.ifPresent(rollingStones::addMember);
        charlieWatts.ifPresent(rollingStones::addMember);
        ronnieWood.ifPresent(rollingStones::addMember);
        bandService.save(rollingStones);

        // Criando repertório e adicionando músicas para a primeira banda
        Repertory repertory1 = new Repertory();
        repertory1.setNome("Show Queen");
        repertory1.setBanda(queen);
        repertoryService.save(repertory1);

        Song song1 = new Song();
        song1.setTitle("Bohemian Rhapsody");
        song1.setPdfPath("path/to/bohemian_rhapsody.pdf");
        song1.setActive(true);
        song1.setBand(queen);
        song1.setRepertorio(repertory1);
        songService.save(song1);

        Song song2 = new Song();
        song2.setTitle("We Will Rock You");
        song2.setPdfPath("path/to/we_will_rock_you.pdf");
        song2.setActive(true);
        song2.setBand(queen);
        song2.setRepertorio(repertory1);
        songService.save(song2);

        Set<Song> musicas1 = new HashSet<>();
        musicas1.add(song1);
        musicas1.add(song2);
        repertory1.setMusicas(musicas1);
        repertoryService.save(repertory1);

        // Criando repertório e adicionando músicas para a segunda banda
        Repertory repertory2 = new Repertory();
        repertory2.setNome("Show The Rolling Stones");
        repertory2.setBanda(rollingStones);
        repertoryService.save(repertory2);

        Song song3 = new Song();
        song3.setTitle("Paint It Black");
        song3.setPdfPath("path/to/paint_it_black.pdf");
        song3.setActive(true);
        song3.setBand(rollingStones);
        song3.setRepertorio(repertory2);
        songService.save(song3);

        Song song4 = new Song();
        song4.setTitle("Angie");
        song4.setPdfPath("path/to/angie.pdf");
        song4.setActive(true);
        song4.setBand(rollingStones);
        song4.setRepertorio(repertory2);
        songService.save(song4);

        Set<Song> musicas2 = new HashSet<>();
        musicas2.add(song3);
        musicas2.add(song4);
        repertory2.setMusicas(musicas2);
        repertoryService.save(repertory2);

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
