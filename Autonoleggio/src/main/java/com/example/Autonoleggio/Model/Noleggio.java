package com.example.Autonoleggio.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "noleggio")
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_inizio_noleggio")
    private LocalDateTime dataInizioNoleggio;
    @Column(name = "data_fine_noleggio")
    private LocalDateTime dataFineNoleggio;
    @JoinColumn(name = "id_profilo", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Profilo profilo;
    @JoinColumn(name = "id_auto", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Auto auto;
    @Column(name = "prezzo_totale")
    private Double prezzoTotale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataInizioNoleggio() {
        return dataInizioNoleggio;
    }

    public void setDataInizioNoleggio(LocalDateTime dataInizioNoleggio) {
        this.dataInizioNoleggio = dataInizioNoleggio;
    }

    public LocalDateTime getDataFineNoleggio() {
        return dataFineNoleggio;
    }

    public void setDataFineNoleggio(LocalDateTime dataFineNoleggio) {
        this.dataFineNoleggio = dataFineNoleggio;
    }

    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

}
