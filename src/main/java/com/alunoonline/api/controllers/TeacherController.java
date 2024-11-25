package com.alunoonline.api.controllers;

import com.alunoonline.api.services.TeacherService;
import com.alunoonline.api.viewobjects.requests.TeacherRequest;
import com.alunoonline.api.viewobjects.responses.TeacherListResponse;
import com.alunoonline.api.viewobjects.responses.TeacherResponse;
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
@RequestMapping("/teachers")
@Tag(name = "Professor", description = "Gerencie os professores")
public class TeacherController {
    final TeacherService service;

    public TeacherController(TeacherService teacherService) {
        this.service = teacherService;
    }

    @Operation(summary = "Cria um professor", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Professor criado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody TeacherRequest request) {

        var teacher = request.toEntity();
        service.create(teacher);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacher.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @Operation(summary = "Edita um professor", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Professor ajustado"), @ApiResponse(responseCode = "404", description = "Professor não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PutMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long teacherId, @Valid @RequestBody TeacherRequest request) {

        var requestEntity = request.toEntity(teacherId);
        var teacherEntity = service.get(teacherId);

        if (teacherEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(teacherEntity.get(), requestEntity);

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Lista professores", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista dos professores"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherListResponse> get() {
        var teachers = service.get();
        var response = new TeacherListResponse(teachers);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Busca um professor por id", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Professor encontrado"), @ApiResponse(responseCode = "404", description = "Professor não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @GetMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherResponse> get(@PathVariable Long teacherId) {
        var teacher = service.get(teacherId);

        if (teacher.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new TeacherResponse(teacher.get());
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Apaga um professor", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Professor apagado"), @ApiResponse(responseCode = "404", description = "Professor não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long teacherId) {
        service.delete(teacherId);
        return ResponseEntity.noContent().build();
    }
}
