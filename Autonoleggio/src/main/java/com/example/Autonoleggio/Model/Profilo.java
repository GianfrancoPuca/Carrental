package com.example.Autonoleggio.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profilo")
public class Profilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Pattern(regexp = "[a-zA-Z\\sàèìòù0-9,._-]{1,50}", message = "Caratteri non ammessi")
    private String username;
    @Column
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}", message = "Password troppo debole")
    private String password;
    @OneToMany(mappedBy = "profilo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Noleggio> noleggi = new ArrayList<>();
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "profilo")
    private Utente utente;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public List<Noleggio> getNoleggi() {
        return noleggi;
    }

    public void setNoleggi(List<Noleggio> noleggi) {
        this.noleggi = noleggi;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
