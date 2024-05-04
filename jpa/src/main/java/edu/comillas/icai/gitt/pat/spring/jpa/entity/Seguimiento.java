package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Seguimiento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public Long id;

    @Column(
            nullable = false
    )
    public Usuario usuario;

    @Column(
            nullable = false
    )
    public Integer clasesQuedan;

    @Column(
            nullable = false
    )
    public Integer clasesAsistidas;
}
