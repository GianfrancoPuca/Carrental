package com.example.Autonoleggio.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String targa;
    @Column
    private String marca;
    @Column
    private String modello;
    @Column(name = "numero_posti")
    private Integer numeroPosti;
    @Column(name = "tipo_cambio")
    private String tipoCambio;
    @Column(name = "tariffa_giornaliera")
    private Double tariffaGiornaliera;
    @Column
    private String alimentazione;
    @Column(name = "numero_porte")
    private Integer numeroPorte;
    @Column(name = "copertura_danni")
    private String coperturaDanni;
    @Column(name = "copertura_furti")
    private String coperturaFurti;
    @Column(name = "noleggio_lungo_termine")
    private String noleggioLungoTermine;
    @Column
    private String foto;
    @Column
    private String descrizione;
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Noleggio> noleggi = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Integer getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(Integer numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Double getTariffaGiornaliera() {
        return tariffaGiornaliera;
    }

    public void setTariffaGiornaliera(Double tariffaGiornaliera) {
        this.tariffaGiornaliera = tariffaGiornaliera;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {
        this.alimentazione = alimentazione;
    }

    public Integer getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(Integer numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public String getCoperturaDanni() {
        return coperturaDanni;
    }

    public void setCoperturaDanni(String coperturaDanni) {
        this.coperturaDanni = coperturaDanni;
    }

    public String getCoperturaFurti() {
        return coperturaFurti;
    }

    public void setCoperturaFurti(String coperturaFurti) {
        this.coperturaFurti = coperturaFurti;
    }

    public String getNoleggioLungoTermine() {
        return noleggioLungoTermine;
    }

    public void setNoleggioLungoTermine(String noleggioLungoTermine) {
        this.noleggioLungoTermine = noleggioLungoTermine;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Noleggio> getNoleggi() {
        return noleggi;
    }

    public void setNoleggi(List<Noleggio> noleggi) {
        this.noleggi = noleggi;
    }
}
