package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Horario {
    @Id
    public Long id;
    @Column
    public Long clase; //foreign key tabla clase (ahi es el id)

    @Column
    public String horario;


}
