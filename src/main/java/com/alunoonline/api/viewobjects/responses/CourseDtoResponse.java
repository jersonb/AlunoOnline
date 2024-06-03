package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Course;

import java.util.List;

public class CourseDtoResponse {
    String professor;
    List<IdNomeViewObject> disciplinas;

    public CourseDtoResponse(List<Course> disciplinas) {

        professor = disciplinas.get(0).getTeacher().getName();

        this.disciplinas = disciplinas
                .stream()
                .map(d -> new IdNomeViewObject(d.getId(), d.getName()))
                .toList();

    }
}
