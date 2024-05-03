package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalTime;

public class Operacion {

    @Id
    public Long id;
    @OneToMany
    @Column
    public Clase clase; //foreign key tabla clase (ahi es el id) --> one t many

    @OneToMany
    @Column Usuario usuario; //Foreign key de la tabla usuario (el id) One to many

    @Column
    public LocalTime hora;
}
