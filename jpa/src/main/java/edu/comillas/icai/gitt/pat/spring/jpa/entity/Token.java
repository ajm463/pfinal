package edu.comillas.icai.gitt.pat.spring.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @OneToOne(optional = false) //relacion de uno a uno
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //para que si borro el usuario,me borre el token
    public Usuario usuario;

}
