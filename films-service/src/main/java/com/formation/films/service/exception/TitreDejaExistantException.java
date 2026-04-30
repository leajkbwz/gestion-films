package com.formation.films.service.exception;

public class TitreDejaExistantException extends RuntimeException {
    public TitreDejaExistantException(String titre) {
        super("Titre déjà existant : " + titre);
    }
}
