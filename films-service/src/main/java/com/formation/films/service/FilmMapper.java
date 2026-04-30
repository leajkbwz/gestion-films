package com.formation.films.service;

import com.formation.films.domain.Film;
import com.formation.films.domain.dto.FilmResponse;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmResponse toResponse(Film film) {
        return new FilmResponse(
            film.getId(),
            film.getTitre(),
            film.getAnnee(),
            film.getDureeMinutes(),
            film.getRealisateur().getNom()
        );
    }
}
