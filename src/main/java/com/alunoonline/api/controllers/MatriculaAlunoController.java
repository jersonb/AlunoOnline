package com.alunoonline.api.controllers;

import com.alunoonline.api.models.MatriculaAluno;
import com.alunoonline.api.services.MatriculaAlunoService;
import com.alunoonline.api.viewobjects.requests.MatriculaAlunoDtoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {
    final MatriculaAlunoService service;

    public MatriculaAlunoController(MatriculaAlunoService service) {
        this.service = service;
    }


    @Operation(summary = "Cria uma matrícula", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula criada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody MatriculaAlunoDtoRequest request) {

        var matriculaAluno = request.toMatriculaAluno();
        service.create(matriculaAluno);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(matriculaAluno.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita uma matrícula", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula ajustado"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PutMapping("/{matriculaAlunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long matriculaAlunoId, @RequestBody MatriculaAlunoDtoRequest request) {

        var matriculaAluno = request.toMatriculaAluno(matriculaAlunoId);
        var matriculaFormDb = service.get(matriculaAlunoId);

        if (matriculaFormDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(matriculaFormDb.get(), matriculaAluno);

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Lista matrículas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de matrículas"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MatriculaAluno>> get() {
        var matriculaAlunos = service.get();
        return ResponseEntity.ok(matriculaAlunos);
    }

    @Operation(summary = "Busca uma matrícula por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/{matriculaAlunoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<MatriculaAluno>> get(@PathVariable Long matriculaAlunoId) {
        var matriculaAluno = service.get(matriculaAlunoId);
        return ResponseEntity.ok(matriculaAluno);
    }
    @Operation(summary = "Apaga uma matrícula", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula deletada"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @DeleteMapping("/{matriculaAlunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long matriculaAlunoId) {
        service.delete(matriculaAlunoId);
        return ResponseEntity.noContent().build();
    }
}
