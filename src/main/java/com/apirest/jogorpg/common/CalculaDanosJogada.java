package com.apirest.jogorpg.common;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class CalculaDanosJogada {

    public static final int BOUND_20 = 20;
    public static final String JOGADOR = "Jogador";
    public static final String MONSTRO = "Monstro";
    @Autowired
    private BatalhaRepository repository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public Batalha calculoDano(Batalha batalha){
        if (batalha.getValorDado() > 0) {
            throw new InvalidInputException("Batalha já finalizada");
        }
        Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByPersonagemMostro(batalha.getMonstro().getPersonagem().getId()));
        Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getPersonagem().getId());

        if (batalha.getIniciativa().equals(JOGADOR)) {
            dano(batalha, jogador, monstro);
            batalha.setIniciativa(MONSTRO);
        }
        else if(batalha.getIniciativa().equals(MONSTRO)) {
            dano(batalha, monstro, jogador);
            batalha.setIniciativa(JOGADOR);
        }
        return repository.save(batalha);
    }

    public void dano(Batalha batalha, Optional<Jogador> jogador, Optional<Jogador> monstro){
        if (jogador.get().getSaldo() > monstro.get().getSaldo()) {
            int dano = jogarDados(monstro.get().getPersonagem().getQtdDado(), monstro.get().getPersonagem().getTolalFaces());
            monstro.get().getPersonagem().setQtdVidas(monstro.get().getPersonagem().getQtdVidas() - dano);
            if (monstro.get().getPersonagem().getQtdVidas() <= 0) {
                batalha.setValorDado(1);
            } else {
                batalha.setTurno(batalha.getTurno() + 1);
            }
            batalha.setMonstro(monstro.get());
        }
    }

    public boolean jogadaDado(){
        boolean result = false;
        Random random = new Random();
        int jogador = random.nextInt(BOUND_20) + 1;
        int monstro = random.nextInt(BOUND_20) + 1;

        if(jogador == monstro){
            jogadaDado();
        }
        if(jogador > monstro){
            result = true;
        }
        return result;
    }

    public int jogarDados(int quantidadeDados, int faces){
        int totalJogadas = 0;
        Random random = new Random();
        for (int i = 0; i < quantidadeDados; i++) {
            totalJogadas += (random.nextInt(faces) + 1);
        }
        return totalJogadas;
    }

}
