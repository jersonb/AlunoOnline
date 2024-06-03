package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Student;
import com.alunoonline.api.models.Course;
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
        var aluno = new Student();
        aluno.setId(alunoId);
        var disciplina = new Course();
        disciplina.setId(disciplinaId);
        matriculaAluno.setAluno(aluno);
        matriculaAluno.setDisciplina(disciplina);
        return matriculaAluno;
    }

    public MatriculaAluno toMatriculaAluno(Long id) {
        var matriculaAluno = new MatriculaAluno();
        var aluno = new Student();
        aluno.setId(alunoId);
        var disciplina = new Course();
        disciplina.setId(disciplinaId);
        matriculaAluno.setAluno(aluno);
        matriculaAluno.setDisciplina(disciplina);
        matriculaAluno.setId(id);
        return matriculaAluno;
    }
}
