package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Disciplina;
import com.alunoonline.api.services.DisciplinaService;
import com.alunoonline.api.viewobjects.requests.DisciplinaDtoRequest;
import com.alunoonline.api.viewobjects.responses.DisciplinaDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplina")
@Tag(name = "Disciplina", description = "Gerencie as disciplinas")
public class DisciplinaController {

    final DisciplinaService service;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.service = disciplinaService;
    }

    @Operation(summary = "Cria ums disciplina", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disciplina criada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody DisciplinaDtoRequest request) {

        var disciplina = request.toDisciplina();
        service.create(disciplina);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(disciplina.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita uma disciplina", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disciplina ajustado"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PutMapping("/{disciplinaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long disciplinaId, @RequestBody DisciplinaDtoRequest request) {

        var disciplina = request.toDisciplina(disciplinaId);
        var disciplinaFormDb = service.get(disciplinaId);

        if (disciplinaFormDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(disciplinaFormDb.get(), disciplina);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista disciplinas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista dos disciplinas"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Disciplina>> get() {
        var disciplinas = service.get();
        return ResponseEntity.ok(disciplinas);
    }

    @Operation(summary = "Busca uma disciplina por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina encontrado"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/{disciplinaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Disciplina>> get(@PathVariable Long disciplinaId) {

        var disciplina = service.get(disciplinaId);
        return ResponseEntity.ok(disciplina);
    }
    @GetMapping("/professor/{id}")
    public  ResponseEntity<DisciplinaDtoResponse> getDisciplina(Long id){

        var disciplinas = service.getDisciplinaByProfessor(id);
        var disciplinaResponse = new DisciplinaDtoResponse(disciplinas);
        return ResponseEntity.ok(disciplinaResponse);
    }
    @Operation(summary = "Edita uma disciplina", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disciplina ajustado"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @DeleteMapping("/{disciplinaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long disciplinaId) {
        service.delete(disciplinaId);
        return ResponseEntity.noContent().build();
    }
}
