package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AlunoDtoRequest {
    @NotBlank(message = "nome precisa ser preenchido")
    private String nome;

    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    @NotBlank
    private String email;

    public Aluno toAluno() {
        var aluno = new Aluno();
        aluno.setNome(this.nome);
        aluno.setEmail(this.email);
        return aluno;
    }

    public Aluno toAluno(Long id) {
        var aluno = new Aluno();
        aluno.setNome(this.nome);
        aluno.setEmail(this.email);
        aluno.setId(id);
        return aluno;
    }
}
