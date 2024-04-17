package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Disciplina;

import java.util.List;

public class DisciplinaDtoResponse {
    String professor;
    List<IdNomeViewObject> disciplinas;

    public DisciplinaDtoResponse(List<Disciplina> disciplinas) {

        professor = disciplinas.get(0).getProfessor().getNome();

        this.disciplinas = disciplinas
                .stream()
                .map(d -> new IdNomeViewObject(d.getId(), d.getNome()))
                .toList();

    }
}
