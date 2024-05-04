package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Horario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public Long id;

    @Column(
            nullable = false
    )
    public Clase clase; //foreign key tabla clase (ahi es el id)

    @Column(
            nullable = false
    )
    public LocalTime hora;


}
