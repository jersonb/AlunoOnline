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
    final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody Aluno aluno){

        alunoService.crete(aluno);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Aluno>> get(){
        var alunos = alunoService.get();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Aluno>> get(@PathVariable Long alunoId){
       var aluno = alunoService.get(alunoId);
       return ResponseEntity.ok(aluno);
    }
}
