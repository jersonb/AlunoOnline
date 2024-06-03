package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Teacher;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TeacherDtoRequest {

    @NotBlank(message = "nome precisa ser preenchido")
    String name;

    @NotBlank
    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    String email;

    public Teacher toEntity() {
        var teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        return teacher;
    }

    public Teacher toEntity(Long id) {
        var teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        teacher.setId(id);
        return teacher;
    }
}
