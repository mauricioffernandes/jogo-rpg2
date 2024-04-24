package com.apirest.jogorpg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BATALHA")
@Entity
public class Batalha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "TURNO")
    private int turno = 1;

    @Column(name = "COD_JOGADOR")
    private Long cod_jogador;

    @Column(name = "INICIATIVA")
    private String iniciativa;

    @Column(name = "VALOR_DADO")
    private int valorDado;

    @Lob
    @Column(name = "JOGADOR", length = 1000)
    private Jogador jogador;

    @Lob
    @Column(name = "MOSTRO", length = 1000)
    private Jogador monstro;
}
