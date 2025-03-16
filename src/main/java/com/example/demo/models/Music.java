package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msc_id")
    private Long id;

    @Column(name = "msc_title")
    private String title;

    @Lob
    @Column(name = "msc_pdf_file")
    @JsonIgnore
    private byte[] pdfFile;

    @Column(name = "msc_registration_date")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "music")
    @Column(name = "msc_repertoires")
    @JsonIgnore
    private List<Repertoire> repertoires;
}