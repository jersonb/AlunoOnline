package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.MatriculaAluno;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class AtualizaNotaDtoRequest {

    @Range(max = 10, min = 0, message = "A nota deve estar entre 0 e 10.")
    Double nota1;

    @Range(max = 10, min = 0, message = "A nota deve estar entre 0 e 10.")
    Double nota2;

    public MatriculaAluno toMatriculaAluno(Long idMatriculaAluno) {
        var matriculaAluno = new MatriculaAluno();
        matriculaAluno.setId(idMatriculaAluno);
        matriculaAluno.setNota1(nota1);
        matriculaAluno.setNota2(nota2);
        return matriculaAluno;
    }
}
