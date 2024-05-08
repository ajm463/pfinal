package edu.comillas.icai.gitt.pat.spring.jpa.model;

public enum Tarifa {
    BASICA(10),
    AVANZADA(20),
    PREMIUM(40);

    private final int horasAsignadas;

    Tarifa(int horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }


}

