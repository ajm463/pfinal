package edu.comillas.icai.gitt.pat.spring.mvc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModeloContador(
        @NotBlank(message = "El nombre no puede estar vacío") String nombre,
        @NotNull(message = "El valor no puede ser nulo") Long valor
) {}


