package com.example.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_MUSICS")
public class MusicModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMusic;
    private String title;
    private String pdfPath;
    private String repertoireName;

    // Getters and Setters

    public UUID getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(UUID idMusic) {
        this.idMusic = idMusic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getRepertoireName() {
        return repertoireName;
    }

    public void setRepertoireName(String repertoireName) {
        this.repertoireName = repertoireName;
    }
}