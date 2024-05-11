package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asegúrate de que esto esté configurado así
    public Long id;

    @Column(nullable = false)
    public String nombre;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String contrasena;

    @Column(nullable = false)
    public Integer tarifa; //variable

    @Column(nullable = false)
    public Integer clasesQuedan;

    @Column(nullable = false)
    public Integer clasesAsistidas;

}