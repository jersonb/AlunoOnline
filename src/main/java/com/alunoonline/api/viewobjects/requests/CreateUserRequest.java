package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserRequest {

    @Length(min = 5, message = "Senha deve ter ao menos 5 caracteres")
    String password;

    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    String email;

    public User toEntity() {
        return this.toEntity(0L);
    }

    public User toEntity(Long id) {
        var user = new User();
        user.setId(id);
        user.setPassword(this.password);
        user.setEmail(this.email);
        return user;
    }
}
