package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String pdfPath;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "banda_id")
    private Band band;

    @ManyToOne
    @JoinColumn(name = "repertorio_id")
    private Repertory repertorio;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Repertory getRepertorio() {
        return repertorio;
    }

    public void setRepertorio(Repertory repertorio) {
        this.repertorio = repertorio;
    }
}
