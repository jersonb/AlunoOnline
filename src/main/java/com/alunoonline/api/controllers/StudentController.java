package com.alunoonline.api.controllers;

import com.alunoonline.api.services.StudentService;
import com.alunoonline.api.viewobjects.requests.StudentRequest;
import com.alunoonline.api.viewobjects.responses.StudentListResponse;
import com.alunoonline.api.viewobjects.responses.StudentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/students")
@Tag(name = "Aluno", description = "Gerencie os alunos")
public class StudentController {
    final StudentService service;

    public StudentController(StudentService studentService) {
        this.service = studentService;
    }

    @Operation(summary = "Adiciona novo aluno", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Aluno adicionado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody StudentRequest request) {

        var student = request.toEntity();
        service.create(student);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @Operation(summary = "Edita um aluno", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Aluno ajustado"), @ApiResponse(responseCode = "404", description = "Aluno não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long studentId, @Valid @RequestBody StudentRequest request) {

        var studentRequest = request.toEntity(studentId);

        var studentEntity = service.get(studentId);

        if (studentEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(studentEntity.get(), studentRequest);

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Lista alunos", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de alunos"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentListResponse> get() {
        var students = service.get();
        var response = new StudentListResponse(students);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Busca um aluno por id", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "500", description = "Erro inesperado"), @ApiResponse(responseCode = "404", description = "Aluno não encontrado"), @ApiResponse(responseCode = "200", description = "Aluno encontrado"),})
    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentResponse> get(@PathVariable Long studentId) {
        var student = service.get(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new StudentResponse(student.get());
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Deleta um aluno por id", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Aluno deletado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"), @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),})
    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        service.delete(studentId);
        return ResponseEntity.noContent().build();
    }
}
