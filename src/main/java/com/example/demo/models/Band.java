package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "band")
public class Band implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bd_id")
    private Long id;

    @Column(name = "bd_nome")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_leader", nullable = false)
    @JsonBackReference
    private User leader;

    @ManyToMany
    @JoinTable(
            name = "band_user",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "band")
    @JsonManagedReference
    private Set<Repertoire> repertoires = new HashSet<>();
}