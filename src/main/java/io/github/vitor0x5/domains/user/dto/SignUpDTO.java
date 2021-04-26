package io.github.vitor0x5.domains.user.dto;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation.PasswordValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SignUpDTO {
    @Email
    private String email;

    @PasswordValidation
    private String password;

    @NotEmpty(message = "Name can't be empty")
    private String name;

    public static AppUser toAppUser(SignUpDTO dto) {
        AppUser user = new AppUser();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
