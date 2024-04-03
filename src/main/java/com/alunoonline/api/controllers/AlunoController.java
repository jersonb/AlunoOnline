package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Aluno;
import com.alunoonline.api.services.AlunoService;
import com.alunoonline.api.viewobjects.requests.AlunoDtoRequest;
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
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "Gerencie os alunos")
public class AlunoController {
    final AlunoService service;

    public AlunoController(AlunoService alunoService) {
        this.service = alunoService;
    }

    @Operation(summary = "Adiciona novo aluno", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno adicionado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody AlunoDtoRequest request) {

        var aluno = request.toAluno();
        service.create(aluno);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita um aluno", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno ajustado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @PutMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long alunoId, @RequestBody AlunoDtoRequest request) {

        var aluno = request.toAluno(alunoId);

        var alunoFomDb = service.get(alunoId);

        if (alunoFomDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(alunoFomDb.get(), aluno);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista alunos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Aluno>> get() {
        var alunos = service.get();
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Busca um aluno por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
    })
    @GetMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Aluno>> get(@PathVariable Long alunoId) {
        var aluno = service.get(alunoId);
        if (aluno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aluno);
    }
    @Operation(summary = "Deleta um aluno por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno deletado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
    })
    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long alunoId) {
        service.delete(alunoId);
        return ResponseEntity.noContent().build();
    }
}
