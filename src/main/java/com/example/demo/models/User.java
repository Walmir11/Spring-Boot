package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usr_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_nome")
    private String name;

    @Column(name = "usr_email", unique = true, nullable = false)
    private String email;

    @Column(name = "usr_senha", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private List<Band> bands;
}