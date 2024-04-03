package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Professor;
import com.alunoonline.api.services.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Gerencie os professores")
public class ProfessorController {
    final ProfessorService service;

    public ProfessorController(ProfessorService professorService) {
        this.service = professorService;
    }
    @Operation(summary = "Cria um professor", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor criado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
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

    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor ajustado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PutMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long professorId, @RequestBody Professor professor) {

        var professorFormDb = service.get(professorId);

        if (professorFormDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var professorExisits = professorFormDb.get();

        professorExisits.setEmail(professor.getEmail());
        professorExisits.setName(professor.getName());


        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor ajustado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> get() {
        var alunos = service.get();
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor ajustado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Professor>> get(@PathVariable Long professorId) {
        var professor = service.get(professorId);
        return ResponseEntity.ok(professor);
    }

    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor ajustado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @DeleteMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long professorId) {
        service.delete(professorId);
        return ResponseEntity.noContent().build();
    }
}
