package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Professor;
import com.alunoonline.api.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    final ProfessorService service;

    public ProfessorController(ProfessorService professorService) {
        this.service = professorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody Professor professor) {

        service.crete(professor);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long professorId, @RequestBody Professor professor) {

        var professorFormDb = service.get(professorId);

        if (professorFormDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(professorFormDb.get(), professor);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> get() {
        var alunos = service.get();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Professor>> get(@PathVariable Long professorId) {
        var professor = service.get(professorId);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long professorId) {
        service.delete(professorId);
        return ResponseEntity.noContent().build();
    }
}
