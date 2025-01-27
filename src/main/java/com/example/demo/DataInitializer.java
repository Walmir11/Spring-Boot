package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    public void run(String... args) throws Exception {
        // Add columns to the tables if they do not exist
        addColumnsToTables();

        // Adding users
        createUserIfNotExists("freddie_mercury", "password1", true, "Queen");
        createUserIfNotExists("brian_may", "password2", false, "Queen");
        createUserIfNotExists("roger_taylor", "password3", false, "Queen");
        createUserIfNotExists("john_deacon", "password4", false, "Queen");

        createUserIfNotExists("mick_jagger", "password5", true, "The Rolling Stones");
        createUserIfNotExists("keith_richards", "password6", false, "The Rolling Stones");
        createUserIfNotExists("charlie_watts", "password7", false, "The Rolling Stones");
        createUserIfNotExists("ronnie_wood", "password8", false, "The Rolling Stones");

        // Adding first band (Queen)
        BandModel queen = new BandModel();
        queen.setName("Queen");
        Optional<UserModel> freddieMercury = userService.getOneUserByUsername("freddie_mercury");
        freddieMercury.ifPresent(queen::setLeader);
        bandService.saveBand(queen);

        // Adding second band (The Rolling Stones)
        BandModel rollingStones = new BandModel();
        rollingStones.setName("The Rolling Stones");
        Optional<UserModel> mickJagger = userService.getOneUserByUsername("mick_jagger");
        mickJagger.ifPresent(rollingStones::setLeader);
        bandService.saveBand(rollingStones);

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

    private void createUserIfNotExists(String username, String password, boolean isLeader, String bandName) {
        Optional<UserModel> existingUser = userService.getOneUserByUsername(username);
        if (!existingUser.isPresent()) {
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setPassword(password); // Set password as plain text
            user.setLeader(isLeader);
            user.setBandName(bandName);
            userService.saveUser(user);
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