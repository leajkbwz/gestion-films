package com.formation.films.domain.dto;

import jakarta.validation.constraints.*;

public record FilmCreateRequest(
    @NotBlank @Size(max = 300) String titre,
    @NotNull @Min(1888) @Max(2100) Integer annee,
    @NotNull @Min(1) Integer dureeMinutes,
    @NotBlank @Size(max = 200) String realisateurNom
) {}
