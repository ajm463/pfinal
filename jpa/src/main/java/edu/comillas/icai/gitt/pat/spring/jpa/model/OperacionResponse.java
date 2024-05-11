package edu.comillas.icai.gitt.pat.spring.jpa.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OperacionResponse(
        @NotBlank
        Long usuario,
        @NotBlank
        String clase,
        @NotNull
        Boolean apuntado) {}


