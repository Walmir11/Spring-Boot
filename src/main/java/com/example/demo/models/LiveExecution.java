package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "live_execution")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lve_nr_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false)
    private Band band;

    @ManyToOne
    @JoinColumn(name = "id_repertoire", nullable = false)
    private Repertoire repertoire;

    @Column(name = "lve_start_date")
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "lve_execution")
    private Boolean execution = true;
}