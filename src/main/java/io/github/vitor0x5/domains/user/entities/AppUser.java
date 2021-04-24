package io.github.vitor0x5.domains.user.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation.PasswordValidation;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.encoder.Encoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {

    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Email can't be empty")
    @Email
    private String email;

    @Transient
    @PasswordValidation
    private String password;

    @Column(name="password")
    @JsonIgnore
    private String encryptedPassword;

    public void encodePassword(String password) {
        Encoder encoder = new Encoder();
        this.encryptedPassword = encoder.encode(password);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}

