package com.formation.films.domain.dto;

public record FilmResponse(
    Long id,
    String titre,
    Integer annee,
    Integer dureeMinutes,
    String realisateurNom
) {}
