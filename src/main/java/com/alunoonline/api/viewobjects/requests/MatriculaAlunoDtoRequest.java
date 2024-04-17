package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Aluno;
import com.alunoonline.api.models.Disciplina;
import com.alunoonline.api.models.MatriculaAluno;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MatriculaAlunoDtoRequest {
    @Min(value = 1, message = "id inválido")
    Long alunoId;
    @Min(value = 1, message = "id inválido")
    Long disciplinaId;

    public MatriculaAluno toMatriculaAluno() {
        var matriculaAluno = new MatriculaAluno();
        var aluno = new Aluno();
        aluno.setId(alunoId);
        var disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        matriculaAluno.setAluno(aluno);
        matriculaAluno.setDisciplina(disciplina);
        return matriculaAluno;
    }

    public MatriculaAluno toMatriculaAluno(Long id) {
        var matriculaAluno = new MatriculaAluno();
        var aluno = new Aluno();
        aluno.setId(alunoId);
        var disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        matriculaAluno.setAluno(aluno);
        matriculaAluno.setDisciplina(disciplina);
        matriculaAluno.setId(id);
        return matriculaAluno;
    }
}