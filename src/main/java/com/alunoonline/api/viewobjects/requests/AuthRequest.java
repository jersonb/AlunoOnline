package com.alunoonline.api.viewobjects.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @Email(message = "O email tá errado.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String username;
    @Length(min = 5, message = "Senha deve ter ao menos 5 caracteres")
    private String password;
}
