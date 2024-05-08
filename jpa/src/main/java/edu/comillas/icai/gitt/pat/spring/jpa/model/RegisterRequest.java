package edu.comillas.icai.gitt.pat.spring.jpa.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        @NotBlank
        String nombre,
        @NotBlank @Email
        String email,
        @NotNull
        Integer tarifa,
        // Patrón: al menos una mayúscula, una minúscula, y un número, y de longitud más de 7
        @NotBlank @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
        String contraseña
) {
}


/* public record RegisterRequest(@NotBlank String name, @NotBlank @Email String email, @NotNull Role role, @NotBlank @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password) {
    public RegisterRequest(@NotBlank String name, @NotBlank @Email String email, @NotNull Role role, @NotBlank @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public @NotBlank String name() {
        return this.name;
    }

    public @NotBlank @Email String email() {
        return this.email;
    }

    public @NotNull Role role() {
        return this.role;
    }

    public @NotBlank @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$"
) String password() {
        return this.password;
    }
}
*/