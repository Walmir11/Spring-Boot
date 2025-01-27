package com.example.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_REPERTORES")
public class RepertoireModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRepertoire;
    private String repertoireName;
    private String bandName;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private BandModel band;

    // Getters and Setters

    public UUID getIdRepertoire() {
        return idRepertoire;
    }

    public void setIdRepertoire(UUID idRepertoire) {
        this.idRepertoire = idRepertoire;
    }

    public String getRepertoireName() {
        return repertoireName;
    }

    public void setRepertoireName(String repertoireName) {
        this.repertoireName = repertoireName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public BandModel getBand() {
        return band;
    }

    public void setBand(BandModel band) {
        this.band = band;
        if (band != null) {
            this.bandName = band.getName();
        }
    }
}