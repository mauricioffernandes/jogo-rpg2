package com.apirest.jogorpg.repository;

import com.apirest.jogorpg.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    @Query(value="select * from jogador j where j.nome = 'Monstro' and j.cod_batalha = ?1", nativeQuery = true)
    public Jogador findByPersonagemMostro(Long codBatalha);
}

