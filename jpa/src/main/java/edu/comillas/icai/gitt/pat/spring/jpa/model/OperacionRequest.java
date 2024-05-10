package edu.comillas.icai.gitt.pat.spring.jpa.model;

public record OperacionRequest(
        Long usuario,
        String clase,
        Boolean apuntado) { // para ver que operación hacemos si apuntar o desapuntar
}
