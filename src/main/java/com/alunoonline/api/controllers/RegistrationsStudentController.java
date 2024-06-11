package com.alunoonline.api.controllers;

import com.alunoonline.api.services.RegistrationStudentService;
import com.alunoonline.api.viewobjects.requests.RegistrationStudentRequest;
import com.alunoonline.api.viewobjects.responses.RegistrationStudentListResponse;
import com.alunoonline.api.viewobjects.responses.RegistrationStudentResponse;
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
@RequestMapping("/registrations-student")
@Tag(name = "Matrículas", description = "Gerencie as matrículas")
public class RegistrationsStudentController {
    final RegistrationStudentService service;

    public RegistrationsStudentController(RegistrationStudentService service) {
        this.service = service;
    }

    @Operation(summary = "Cria uma matrícula", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Matrícula criada"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody RegistrationStudentRequest request) {

        var registrationStudent = request.toEntity();
        service.create(registrationStudent);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registrationStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita uma matrícula", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Matrícula ajustado"), @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"), @ApiResponse(responseCode = "500", description = "Erro inesperado")})
    @PutMapping("/{registrationStudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long registrationStudentId, @Valid @RequestBody RegistrationStudentRequest request) {

        var registrationStudentRequest = request.toEntity(registrationStudentId);
        var registrationStudentEntity = service.get(registrationStudentId);

        if (registrationStudentEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(registrationStudentEntity.get(), registrationStudentRequest);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista matrículas", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de matrículas"), @ApiResponse(responseCode = "500", description = "Erro inesperado")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegistrationStudentListResponse> get() {
        var registrations = service.get();

        var response = new RegistrationStudentListResponse(registrations);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca uma matrícula por id", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Matrícula encontrada"), @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @GetMapping("/{registrationStudentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegistrationStudentResponse> get(@PathVariable Long registrationStudentId) {
        var registrationStudent = service.get(registrationStudentId);
        if (registrationStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new RegistrationStudentResponse(registrationStudent.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Apaga uma matrícula", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Matrícula deletada"), @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"), @ApiResponse(responseCode = "500", description = "Erro inesperado")})
    @DeleteMapping("/{registrationStudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long registrationStudentId) {
        service.delete(registrationStudentId);
        return ResponseEntity.noContent().build();
    }
}
