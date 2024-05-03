package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Horario {
    @Id
    public Long id;
    @Column
    public Clase clase; //foreign key tabla clase (ahi es el id)

    @Column
    public LocalTime hora;


}
