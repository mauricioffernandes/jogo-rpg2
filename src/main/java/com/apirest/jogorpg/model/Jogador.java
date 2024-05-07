package com.apirest.jogorpg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JOGADOR")
@Entity
public class Jogador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "NOME")
    private String nome;

    @Lob
    @Column(name = "PERSONAGEM", length = 1000)
    private Personagem personagem;

    @Column(name = "SALDO")
    private int saldo;

}
