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
    @Column
    public Usuario usuario; //Foreign key de la tabla usuario (el id) One to many



    @OneToMany
    @Column
    public Seguimiento seguimiento;

    @Column
    public LocalTime hora;
}
