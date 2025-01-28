package com.example.springboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PROFILES", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ProfileModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProfile;
    private String name;
    private String puTxStatus;
    private String perTxStatus;

    public UUID getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(UUID idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPuTxStatus() {
        return puTxStatus;
    }

    public void setPuTxStatus(String puTxStatus) {
        this.puTxStatus = puTxStatus;
    }

    public String getPerTxStatus() {
        return perTxStatus;
    }

    public void setPerTxStatus(String perTxStatus) {
        this.perTxStatus = perTxStatus;
    }
}