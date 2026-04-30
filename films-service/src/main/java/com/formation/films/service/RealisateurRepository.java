package com.formation.films.service;

import com.formation.films.domain.Realisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealisateurRepository extends JpaRepository<Realisateur, Long> {
    Optional<Realisateur> findByNom(String nom);
}
