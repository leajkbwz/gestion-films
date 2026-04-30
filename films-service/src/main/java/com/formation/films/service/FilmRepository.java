package com.formation.films.service;

import com.formation.films.domain.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByTitre(String titre);

    boolean existsByTitre(String titre);

    Page<Film> findByRealisateurNomContainingIgnoreCase(String nom, Pageable pageable);
}
