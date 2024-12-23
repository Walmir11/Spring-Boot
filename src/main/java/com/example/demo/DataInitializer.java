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

        addSongToRepertory("Bohemian Rhapsody", "path/to/bohemian_rhapsody.pdf", queen, repertory1);
        addSongToRepertory("We Will Rock You", "path/to/we_will_rock_you.pdf", queen, repertory1);

        // Criando repertório e adicionando músicas para a segunda banda
        Repertory repertory2 = new Repertory();
        repertory2.setNome("Show The Rolling Stones");
        repertory2.setBanda(rollingStones);
        repertoryService.save(repertory2);

        addSongToRepertory("Paint It Black", "path/to/paint_it_black.pdf", rollingStones, repertory2);
        addSongToRepertory("Angie", "path/to/angie.pdf", rollingStones, repertory2);

        System.out.println("Dados iniciais adicionados e operações simuladas.");
    }

    private void addSongToRepertory(String title, String pdfPath, Band band, Repertory repertory) {
        Optional<Song> existingSong = songService.findByTitleAndBand(title, band);
        Song song;
        if (existingSong.isPresent()) {
            song = existingSong.get();
        } else {
            song = new Song();
            song.setTitle(title);
            song.setPdfPath(pdfPath);
            song.setActive(true);
            song.setBand(band);
            songService.save(song);
        }
        song.setRepertorio(repertory);
        songService.save(song);

        Set<Song> musicas = repertory.getMusicas();
        if (musicas == null) {
            musicas = new HashSet<>();
        }
        musicas.add(song);
        repertory.setMusicas(musicas);
        repertoryService.save(repertory);
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
