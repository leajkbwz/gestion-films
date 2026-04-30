package com.formation.films.service;

import com.formation.films.domain.Film;
import com.formation.films.domain.Realisateur;
import com.formation.films.domain.dto.FilmCreateRequest;
import com.formation.films.domain.dto.FilmResponse;
import com.formation.films.domain.dto.FilmUpdateRequest;
import com.formation.films.service.exception.FilmNotFoundException;
import com.formation.films.service.exception.TitreDejaExistantException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FilmService {

    private final FilmRepository filmRepo;
    private final RealisateurRepository realisateurRepo;
    private final FilmMapper mapper;

    public FilmService(FilmRepository filmRepo, RealisateurRepository realisateurRepo, FilmMapper mapper) {
        this.filmRepo = filmRepo;
        this.realisateurRepo = realisateurRepo;
        this.mapper = mapper;
    }

    public FilmResponse create(FilmCreateRequest req) {
        if (filmRepo.existsByTitre(req.titre())) {
            throw new TitreDejaExistantException(req.titre());
        }
        Realisateur realisateur = realisateurRepo.findByNom(req.realisateurNom())
            .orElseGet(() -> realisateurRepo.save(new Realisateur(req.realisateurNom())));

        Film film = new Film(req.titre(), req.annee(), req.dureeMinutes(), realisateur);
        Film saved = filmRepo.save(film);
        return mapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public FilmResponse findById(Long id) {
        Film film = filmRepo.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
        return mapper.toResponse(film);
    }

    @Transactional(readOnly = true)
    public Page<FilmResponse> findAll(Pageable pageable) {
        return filmRepo.findAll(pageable).map(mapper::toResponse);
    }

    public FilmResponse update(Long id, FilmUpdateRequest req) {
        Film film = filmRepo.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));

        Realisateur realisateur = realisateurRepo.findByNom(req.realisateurNom())
            .orElseGet(() -> realisateurRepo.save(new Realisateur(req.realisateurNom())));

        film.setTitre(req.titre());
        film.setAnnee(req.annee());
        film.setDureeMinutes(req.dureeMinutes());
        film.setRealisateur(realisateur);
        return mapper.toResponse(film);
    }

    public void delete(Long id) {
        if (!filmRepo.existsById(id)) {
            throw new FilmNotFoundException(id);
        }
        filmRepo.deleteById(id);
    }
}
