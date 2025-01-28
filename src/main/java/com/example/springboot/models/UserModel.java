package com.example.springboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usu_usuario")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Integer id;

    @Column(name = "usu_tx_login", nullable = false, length = 100)
    private String login;

    @Column(name = "usu_tx_senha", nullable = false, length = 999)
    private String password;

    @Column(name = "usu_tx_nome", nullable = false, length = 100)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "pu_per_usu",
            joinColumns = @JoinColumn(name = "usu_nr_id", referencedColumnName = "usu_nr_id"),
            inverseJoinColumns = @JoinColumn(name = "per_nr_id", referencedColumnName = "per_nr_id")
    )
    private Set<ProfileModel> profiles;

    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProfileModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<ProfileModel> profiles) {
        this.profiles = profiles;
    }
}