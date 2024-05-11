package edu.comillas.icai.gitt.pat.spring.jpa.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OperacionRequest(
        @NotBlank
        Long usuario,
        @NotBlank
        String clase,
        @NotNull
        Boolean apuntado) { // para ver que operación hacemos si apuntar o desapuntar
}
