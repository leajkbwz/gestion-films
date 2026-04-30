package com.formation.films.service;

import com.formation.films.domain.Film;
import com.formation.films.domain.Realisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FilmRepositoryTest {

    @Autowired FilmRepository filmRepo;
    @Autowired TestEntityManager em;

    @Test
    void findByTitre_existant_renvoieLeFilm() {
        Realisateur nolan = em.persist(new Realisateur("Christopher Nolan"));
        em.persist(new Film("Inception", 2010, 148, nolan));
        em.flush();

        assertThat(filmRepo.findByTitre("Inception")).isPresent();
    }

    @Test
    void findByTitre_inconnu_renvoieEmpty() {
        assertThat(filmRepo.findByTitre("inconnu")).isEmpty();
    }

    @Test
    void existsByTitre() {
        Realisateur r = em.persist(new Realisateur("R"));
        em.persist(new Film("T", 2020, 100, r));
        em.flush();

        assertThat(filmRepo.existsByTitre("T")).isTrue();
        assertThat(filmRepo.existsByTitre("Y")).isFalse();
    }
}
