package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.UserInfo;
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

    public UserInfo toEntity() {
        return this.toEntity(0L);
    }

    public UserInfo toEntity(Long id) {
        var user = new UserInfo();
        user.setId(id);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setRoles("USER");
        user.setName(this.email);
        return user;
    }
}
