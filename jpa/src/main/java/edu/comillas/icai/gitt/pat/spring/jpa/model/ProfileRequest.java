package edu.comillas.icai.gitt.pat.spring.jpa.model;


import jakarta.validation.constraints.Pattern;

public record ProfileRequest(
        String nombre,
        Integer tarifa,
        // Patrón: al menos una mayúscula, una minúscula, y un número, y de longitud más de 7
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")
        String contraseña
) {}
