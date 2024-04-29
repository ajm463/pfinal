package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Clase {
    @Id
    public Long id;
    @Column
    public String nombre;

    @Column
    public String nivel;

    @Column
    public Integer capacidad;



}
