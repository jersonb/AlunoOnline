package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Professor;
import com.alunoonline.api.services.ProfessorService;
import com.alunoonline.api.viewobjects.requests.ProfessorDtoRequest;
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
    public ResponseEntity<Object> create(@RequestBody ProfessorDtoRequest request) {

        var professor = request.toProfessor();
        service.create(professor);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Professor ajustado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PutMapping("/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long professorId, @RequestBody ProfessorDtoRequest request) {

        var professor = request.toProfessor(professorId);
        var professorFormDb = service.get(professorId);

        if (professorFormDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(professorFormDb.get(), professor);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista professores", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista dos professores"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> get() {
        var professores = service.get();
        return ResponseEntity.ok(professores);
    }

    @Operation(summary = "Busca um professor por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor encontrado"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Professor>> get(@PathVariable Long professorId) {
        var professor = service.get(professorId);
        return ResponseEntity.ok(professor);
    }

    @Operation(summary = "Apaga um professor", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor apagado"),
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
