package com.example.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "leaders")
public class BandModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBand;
    private String name;
    private String leaderName;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private UserModel leader;

    @OneToMany(mappedBy = "band")
    private Set<RepertoireModel> repertoires;

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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public UserModel getLeader() {
        return leader;
    }

    public void setLeader(UserModel leader) {
        this.leader = leader;
        if (leader != null) {
            this.leaderName = leader.getUsername();
        }
    }

    public Set<RepertoireModel> getRepertoires() {
        return repertoires;
    }

    public void setRepertoires(Set<RepertoireModel> repertoires) {
        this.repertoires = repertoires;
    }
}