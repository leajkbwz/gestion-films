package com.formation.films.service;

import com.formation.films.domain.dto.FilmCreateRequest;
import com.formation.films.service.exception.FilmNotFoundException;
import com.formation.films.service.exception.TitreDejaExistantException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock FilmRepository filmRepo;
    @Mock RealisateurRepository realisateurRepo;
    @Mock FilmMapper mapper;

    @InjectMocks FilmService service;

    @Test
    void create_titreDejaExistant_leveException() {
        when(filmRepo.existsByTitre("Inception")).thenReturn(true);

        assertThatThrownBy(() -> service.create(
                new FilmCreateRequest("Inception", 2010, 148, "Nolan")))
            .isInstanceOf(TitreDejaExistantException.class);

        verify(filmRepo, never()).save(any());
    }

    @Test
    void findById_inconnu_leveException() {
        when(filmRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
            .isInstanceOf(FilmNotFoundException.class);
    }
}
