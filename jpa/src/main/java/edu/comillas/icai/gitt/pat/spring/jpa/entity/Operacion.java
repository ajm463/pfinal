package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "clase_id", referencedColumnName = "id", nullable= false)
    public Clase clase; //foreign key tabla clase (ahi es el id) --> one t many

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable= false)
    public Usuario usuario; //Foreign key de la tabla usuario (el id) One to many


    @Column(nullable = false)
    public LocalTime horaOperacion;
}
