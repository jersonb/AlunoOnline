package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Disciplina;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DisciplinaDtoRequest {
    @NotBlank(message = "nome precisa ser preenchido")
    String nome;

    public Disciplina toDisciplina() {
        var disciplina = new Disciplina();
        disciplina.setNome(this.nome);
        return disciplina;
    }

    public Disciplina toDisciplina(Long id) {
        var disciplina = new Disciplina();
        disciplina.setNome(this.nome);
        disciplina.setId(id);
        return disciplina;
    }
}
