package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Professor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProfessorDtoRequest {

    @NotBlank(message = "nome precisa ser preenchido")
    String nome;

    @NotBlank
    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    String email;

    public Professor toProfessor() {
        var professor = new Professor();
        professor.setEmail(email);
        professor.setNome(nome);
        return professor;
    }

    public Professor toProfessor(Long id) {
        var professor = new Professor();
        professor.setEmail(email);
        professor.setNome(nome);
        professor.setId(id);
        return professor;
    }
}
