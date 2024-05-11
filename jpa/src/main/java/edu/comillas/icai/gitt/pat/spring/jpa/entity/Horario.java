package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "clase_id", referencedColumnName = "id")
    public Clase clase;

    @Column(nullable = false)
    public LocalTime horario;

    @Column(nullable = false)
    public String dia;  // Agregar este campo
}


