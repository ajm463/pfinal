package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seguimiento {
    @Id
    public Long id;
    @Column
    public Usuario usuario;
    @Column
    public Integer clasesQuedan;
    @Column
    public Integer clasesAsistidas;
}
