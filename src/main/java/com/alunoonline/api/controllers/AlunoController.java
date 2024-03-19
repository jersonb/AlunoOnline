package com.alunoonline.api.controllers;

import com.alunoonline.api.models.Aluno;
import com.alunoonline.api.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    final AlunoService service;

    public AlunoController(AlunoService alunoService) {
        this.service = alunoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody Aluno aluno) {

        service.crete(aluno);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{aluniId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long aluniId, @RequestBody Aluno aluno) {

        var alunoFomDb = service.get(aluniId);

        if (alunoFomDb.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var alunoExisits = alunoFomDb.get();

        alunoExisits.setEmail(aluno.getEmail());
        alunoExisits.setName(aluno.getName());


        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Aluno>> get() {
        var alunos = service.get();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Aluno>> get(@PathVariable Long alunoId) {
        var aluno = service.get(alunoId);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long alunoId) {
       service.delete(alunoId);
        return ResponseEntity.noContent().build();
    }
}
