package com.example.springboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS", uniqueConstraints = @UniqueConstraint(columnNames = "password"))
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_profiles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "idProfile")
    )
    private Set<ProfileModel> profiles;

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<ProfileModel> profiles) {
        this.profiles = profiles;
    }
}