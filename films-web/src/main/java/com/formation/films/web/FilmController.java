package com.formation.films.web;

import com.formation.films.domain.dto.FilmCreateRequest;
import com.formation.films.domain.dto.FilmResponse;
import com.formation.films.domain.dto.FilmUpdateRequest;
import com.formation.films.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FilmResponse> create(@Valid @RequestBody FilmCreateRequest req) {
        FilmResponse created = service.create(req);
        return ResponseEntity
            .created(URI.create("/api/films/" + created.id()))
            .body(created);
    }

    @GetMapping
    public Page<FilmResponse> list(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public FilmResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public FilmResponse update(@PathVariable Long id,
                               @Valid @RequestBody FilmUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
