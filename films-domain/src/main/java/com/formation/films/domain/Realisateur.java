package com.formation.films.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "realisateurs")
public class Realisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String nom;

    protected Realisateur() {}

    public Realisateur(String nom) {
        this.nom = Objects.requireNonNull(nom);
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Realisateur r && Objects.equals(id, r.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}
