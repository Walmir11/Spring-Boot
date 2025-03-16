package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "repertoire", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"band_id", "music_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repertoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rpr_id")
    private Long id;

    @Column(name = "rpr_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    @JsonBackReference
    private Band band;

    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @Column(name = "rpr_active")
    private Boolean active = true;

    @Column(name = "rpr_posicion")
    private Integer position;
}