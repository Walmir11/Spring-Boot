package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Repertory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "banda_id")
    private Band banda;

    @OneToMany(mappedBy = "repertorio")
    private Set<Song> musicas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Band getBanda() {
        return banda;
    }

    public void setBanda(Band banda) {
        this.banda = banda;
    }

    public Set<Song> getMusicas() {
        return musicas;
    }

    public void setMusicas(Set<Song> musicas) {
        this.musicas = musicas;
    }
}
