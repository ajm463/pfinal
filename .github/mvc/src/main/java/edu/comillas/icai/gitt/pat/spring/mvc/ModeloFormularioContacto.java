package edu.comillas.icai.gitt.pat.spring.mvc;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModeloFormularioContacto(

        @Size(max=20, message = "El mensaje no puede superar los 20 caracteres")
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        @Email(message = "El formato del email es incorrecto")
        @NotBlank(message = "El email es obligatorio")
        String email

) {}

