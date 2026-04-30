package com.formation.films.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.films.domain.dto.FilmCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class FilmControllerIT {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper json;

    @Test
    void post_creeUnFilm_etRetourne201() throws Exception {
        FilmCreateRequest req = new FilmCreateRequest(
            "Memento", 2000, 113, "Christopher Nolan");

        mvc.perform(post("/api/films")
                .contentType("application/json")
                .content(json.writeValueAsString(req)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.titre").value("Memento"))
            .andExpect(jsonPath("$.realisateurNom").value("Christopher Nolan"));
    }

    @Test
    void post_donneesInvalides_retourne400() throws Exception {
        String invalid = """
            { "titre": "", "annee": 1500, "dureeMinutes": 0, "realisateurNom": "" }
            """;
        mvc.perform(post("/api/films")
                .contentType("application/json")
                .content(invalid))
            .andExpect(status().isBadRequest());
    }

    @Test
    void get_idInconnu_retourne404() throws Exception {
        mvc.perform(get("/api/films/99999"))
            .andExpect(status().isNotFound());
    }
}
