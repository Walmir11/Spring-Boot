package com.example.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BANDS")
public class BandModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBand;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "band_user",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserModel> members = new HashSet<>();

    // Getters and Setters

    public UUID getIdBand() {
        return idBand;
    }

    public void setIdBand(UUID idBand) {
        this.idBand = idBand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserModel> getMembers() {
        return members;
    }

    public void setMembers(Set<UserModel> members) {
        this.members = members;
    }
}