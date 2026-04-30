package com.formation.films.service.exception;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super("Film introuvable : id=" + id);
    }
    public FilmNotFoundException(String titre) {
        super("Film introuvable : titre=" + titre);
    }
}
