package io.github.vitor0x5.domains.user.dto;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation.PasswordValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SignInDTO {

    @Email(message = "invalid email address")
    @NotNull(message = "email can't be null")
    private String email;


    @NotNull(message = "password can't be null")
    private String password;

    public static AppUser toAppUser(SignUpDTO signUpDTO) {
        AppUser user = new AppUser();
        user.setEmail(signUpDTO.getEmail());
        user.setName(signUpDTO.getName());
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
}
