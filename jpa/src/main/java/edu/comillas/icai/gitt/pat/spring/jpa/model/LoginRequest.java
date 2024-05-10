package edu.comillas.icai.gitt.pat.spring.jpa.model;

import jakarta.validation.constraints.NotBlank;

//TODO#4 modificar los modelos para luego poder hacer el servicio
public record LoginRequest(
        @NotBlank String email,
        @NotBlank String contrasena){

}
