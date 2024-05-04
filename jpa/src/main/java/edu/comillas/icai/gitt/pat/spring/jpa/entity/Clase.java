package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

@Entity
public class Clase {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public Long id;

    @Column(
            nullable = false
    )
    public String nombre;
    @OneToMany
    @JoinColumn(
            name = " horario_id",
            referencedColumnName = "id",
            nullable= false)
    public Horario horario;

    @Column(
            nullable = false
    )
    public String nivel;

    @Column(
            nullable = false
    )
    public Integer capacidad;



}
