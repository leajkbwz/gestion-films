package com.formation.films.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class FilmTest {

    @Test
    void creation_renseigne_les_champs() {
        Realisateur realisateur = new Realisateur("Christopher Nolan");
        Film film = new Film("Inception", 2010, 148, realisateur);

        assertThat(film.getTitre()).isEqualTo("Inception");
        assertThat(film.getAnnee()).isEqualTo(2010);
        assertThat(film.getDureeMinutes()).isEqualTo(148);
        assertThat(film.getRealisateur().getNom()).isEqualTo("Christopher Nolan");
    }
}
