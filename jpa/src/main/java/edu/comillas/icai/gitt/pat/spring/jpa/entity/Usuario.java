package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    public Long id;
    @Column
    public String nombre;
    @Column
    public String email;
    @Column
    public String contraseña;
    @Column
    public String tarifa;
    //pruebaaaaa
}
