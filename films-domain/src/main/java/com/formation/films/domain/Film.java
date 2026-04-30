package com.formation.films.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 300)
    private String titre;

    @Column(nullable = false)
    private Integer annee;

    @Column(name = "duree_minutes", nullable = false)
    private Integer dureeMinutes;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    protected Film() {}

    public Film(String titre, Integer annee, Integer dureeMinutes, Realisateur realisateur) {
        this.titre = titre;
        this.annee = annee;
        this.dureeMinutes = dureeMinutes;
        this.realisateur = realisateur;
    }

    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public Integer getAnnee() { return annee; }
    public Integer getDureeMinutes() { return dureeMinutes; }
    public Realisateur getRealisateur() { return realisateur; }

    public void setTitre(String titre) { this.titre = titre; }
    public void setAnnee(Integer annee) { this.annee = annee; }
    public void setDureeMinutes(Integer dureeMinutes) { this.dureeMinutes = dureeMinutes; }
    public void setRealisateur(Realisateur realisateur) { this.realisateur = realisateur; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Film f && Objects.equals(id, f.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}
