package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public Long id;

    @Column(
            nullable = false
    )
    public String nombre;
    @Column(
            nullable = false
    )
    public String email;

    @Column(
            nullable = false
    )
    public String contraseña;

    @Column(
            nullable = false
    )
    public float tarifa; //variable

    //pruebaa
    //prueba marian

}
