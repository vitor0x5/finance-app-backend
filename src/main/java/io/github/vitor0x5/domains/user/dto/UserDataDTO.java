package io.github.vitor0x5.domains.user.dto;

public class UserDataDTO {
    private String name;
    private String email;

    public UserDataDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
