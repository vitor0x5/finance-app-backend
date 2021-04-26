package io.github.vitor0x5.domains.user.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.vitor0x5.shared.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
    private String name;
    private String email;

    @JsonIgnore
    private String password;

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
}

