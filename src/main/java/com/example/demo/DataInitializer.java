package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BandService bandService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private RepertoireService repertoireService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Add columns to the tables if they do not exist
        addColumnsToTables();

        // Adding users
        createUserIfNotExists("freddie_mercury", "password1", true);
        createUserIfNotExists("brian_may", "password2", false);
        createUserIfNotExists("roger_taylor", "password3", false);
        createUserIfNotExists("john_deacon", "password4", false);

        createUserIfNotExists("mick_jagger", "password5", true);
        createUserIfNotExists("keith_richards", "password6", false);
        createUserIfNotExists("charlie_watts", "password7", false);
        createUserIfNotExists("ronnie_wood", "password8", false);

        // Adding first band (Queen)
        BandModel queen = new BandModel();
        queen.setName("Queen");
        bandService.saveBand(queen);

        // Adding second band (The Rolling Stones)
        BandModel rollingStones = new BandModel();
        rollingStones.setName("The Rolling Stones");
        bandService.saveBand(rollingStones);

        // Adding users to bands
        addUserToBand("freddie_mercury", queen);
        addUserToBand("brian_may", queen);
        addUserToBand("roger_taylor", queen);
        addUserToBand("john_deacon", queen);

        addUserToBand("mick_jagger", rollingStones);
        addUserToBand("keith_richards", rollingStones);
        addUserToBand("charlie_watts", rollingStones);
        addUserToBand("ronnie_wood", rollingStones);

        // Example: Adding "freddie_mercury" to both "Queen" and "The Rolling Stones"
        addUserToBand("freddie_mercury", rollingStones);

        // Creating repertoire and adding songs for the first band
        RepertoireModel repertoire1 = new RepertoireModel();
        repertoire1.setBand(queen);
        repertoire1.setRepertoireName("Queen Repertoire");
        repertoireService.saveRepertoire(repertoire1);

        addMusicToRepertoire("Bohemian Rhapsody", "path/to/bohemian_rhapsody.pdf", repertoire1);
        addMusicToRepertoire("We Will Rock You", "path/to/we_will_rock_you.pdf", repertoire1);

        // Creating repertoire and adding songs for the second band
        RepertoireModel repertoire2 = new RepertoireModel();
        repertoire2.setBand(rollingStones);
        repertoire2.setRepertoireName("Rolling Stones Repertoire");
        repertoireService.saveRepertoire(repertoire2);

        addMusicToRepertoire("Paint It Black", "path/to/paint_it_black.pdf", repertoire2);
        addMusicToRepertoire("Angie", "path/to/angie.pdf", repertoire2);

        System.out.println("Initial data added and operations simulated.");
    }

    private void addColumnsToTables() {
        // Check if tb_repertoires table exists
        if (tableExists("tb_repertoires")) {
            if (!columnExists("tb_repertoires", "repertoire_name")) {
                jdbcTemplate.execute("ALTER TABLE tb_repertoires ADD COLUMN repertoire_name VARCHAR(255)");
            }
            if (!columnExists("tb_repertoires", "band_id")) {
                jdbcTemplate.execute("ALTER TABLE tb_repertoires ADD COLUMN band_id UUID");
            }
        }

        // Check if tb_musics table exists
        if (tableExists("tb_musics")) {
            if (!columnExists("tb_musics", "repertoire_name")) {
                jdbcTemplate.execute("ALTER TABLE tb_musics ADD COLUMN repertoire_name VARCHAR(255)");
            }
        }
    }

    private boolean tableExists(String tableName) {
        String query = String.format(
                "SELECT COUNT(*) FROM information_schema.tables WHERE table_name='%s'",
                tableName
        );
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count != null && count > 0;
    }

    private boolean columnExists(String tableName, String columnName) {
        String query = String.format(
                "SELECT COUNT(*) FROM information_schema.columns WHERE table_name='%s' AND column_name='%s'",
                tableName, columnName
        );
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count != null && count > 0;
    }

    private void createUserIfNotExists(String username, String password, boolean isLeader) {
        Optional<UserModel> existingUser = userService.getOneUserByUsername(username);
        if (!existingUser.isPresent()) {
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setPassword(password); // Set password as plain text
            user.setLeader(isLeader);
            userService.saveUser(user);
        }
    }

    private void addUserToBand(String username, BandModel band) {
        Optional<UserModel> userO = userService.getOneUserByUsername(username);
        if (userO.isPresent()) {
            UserModel user = userO.get();
            user.getBands().add(band);
            band.getMembers().add(user);
            userService.saveUser(user);
            bandService.saveBand(band);
        }
    }

    private void addMusicToRepertoire(String title, String pdfPath, RepertoireModel repertoire) {
        MusicModel music = new MusicModel();
        music.setTitle(title);
        music.setPdfPath(pdfPath);
        music.setRepertoireName(repertoire.getRepertoireName());
        musicService.saveMusic(music);
    }
}