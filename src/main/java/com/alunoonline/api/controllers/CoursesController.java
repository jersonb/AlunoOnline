package com.alunoonline.api.controllers;

import com.alunoonline.api.services.CourseService;
import com.alunoonline.api.viewobjects.requests.CourseRequest;
import com.alunoonline.api.viewobjects.responses.CourseListResponse;
import com.alunoonline.api.viewobjects.responses.CourseResponse;
import com.alunoonline.api.viewobjects.responses.CourseTeacherResponse;
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
@RequestMapping("/courses")
@Tag(name = "Disciplina", description = "Gerencie as disciplinas")
@RestControllerAdvice
public class CoursesController {

    final CourseService service;

    public CoursesController(CourseService courseService) {
        this.service = courseService;
    }

    @Operation(summary = "Cria ums disciplina", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Disciplina criada"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @RequestBody CourseRequest request) {

        var course = request.toEntity();
        service.create(course);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Edita uma disciplina", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Disciplina ajustado"), @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PutMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long courseId, @Valid @RequestBody CourseRequest request) {

        var course = request.toEntity(courseId);
        var courseEntity = service.get(courseId);

        if (courseEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.update(courseEntity.get(), course);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista disciplinas", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista dos disciplinas"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseListResponse> get() {
        var courses = service.get();

        var courseListResponse = new CourseListResponse(courses);
        return ResponseEntity.ok(courseListResponse);
    }

    @Operation(summary = "Busca uma disciplina por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina encontrado"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro inesperado")
    })
    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseResponse> get(@PathVariable Long courseId) {

        var course = service.get(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var response = new CourseResponse(course.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edita uma disciplina", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Disciplina ajustado"), @ApiResponse(responseCode = "404", description = "Disciplina não encontrado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        service.delete(courseId);
        return ResponseEntity.noContent().build();
    }
}
