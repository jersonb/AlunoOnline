package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentRequest {
    @NotBlank(message = "nome precisa ser preenchido")
    private String name;

    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    @NotBlank
    private String email;

    public Student toEntity() {
        return this.toEntity(0L);
    }

    public Student toEntity(Long id) {
        var student = new Student();
        student.setName(this.name);
        student.setEmail(this.email);
        student.setId(id);
        return student;
    }
}
