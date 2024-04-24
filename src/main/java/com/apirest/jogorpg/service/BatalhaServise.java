package com.apirest.jogorpg.service;

import com.apirest.jogorpg.common.CalculaDanosJogada;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BatalhaServise extends CalculaDanosJogada {

    public static final int QUANTIDADE_DADOS = 1;
    public static final int TOTAL_FACES = 12;
    public static final String MONSTRO = "Monstro";
    public static final String JOGADOR = "Jogador";
    @Autowired
    private BatalhaRepository repository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private JogadorService jogadorService;

    public List<Batalha> findAll(){
        return repository.findAll();
    }

    public Batalha getById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Batalha not found with ID: " + id
        ));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Batalha create(Long cod_jogador){
        Batalha batalha = new Batalha();
        Optional<Jogador> jogador = jogadorRepository.findById(cod_jogador);
        Optional<Jogador> monstro = Optional.ofNullable(jogadorService.createMonstros(cod_jogador));

        batalha.setCreatedAt(LocalDateTime.now());
        batalha.setJogador(jogador.get());
        batalha.setMonstro(monstro.get());

        batalha.setTurno(batalha.getTurno());
        batalha.setCod_jogador(jogador.get().getId());
        if(jogadaDado()){
            batalha.setIniciativa(jogador.get().getNome());
        }
        else{
            batalha.setIniciativa(monstro.get().getNome());
        }
        return repository.save(batalha);
    }

    public Batalha ataque(Batalha batalha){
        if (batalha.getValorDado() > 0) {
            throw new InvalidInputException("A Batalha não existe ou já foi finalizada");
        }
        if(batalha.getIniciativa().equals(MONSTRO)) {
            Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByPersonagemMostro(batalha.getMonstro().getPersonagem().getId()));
            monstro.get().setSaldo(jogarDados(QUANTIDADE_DADOS, TOTAL_FACES) + batalha.getJogador().getPersonagem().getPoder() + batalha.getJogador().getPersonagem().getAgilidade());
            batalha.setMonstro(monstro.get());
            batalha.setIniciativa(JOGADOR);
        }
        else{
            Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getPersonagem().getId());
            jogador.get().setSaldo(jogarDados(QUANTIDADE_DADOS, TOTAL_FACES) + batalha.getMonstro().getPersonagem().getPoder() + batalha.getMonstro().getPersonagem().getAgilidade());
            batalha.setJogador(jogador.get());
            batalha.setIniciativa(MONSTRO);
        }
        return repository.save(batalha);
    }

    public Batalha defesa(Batalha batalha){
        if (batalha.getValorDado() > 0) {
            throw new InvalidInputException("A Batalha não existe ou já foi finalizada");
        }
        if(batalha.getIniciativa().equals(MONSTRO)) {
            Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByPersonagemMostro(batalha.getMonstro().getPersonagem().getId()));
            monstro.get().setSaldo(jogarDados(QUANTIDADE_DADOS, TOTAL_FACES) + batalha.getJogador().getPersonagem().getDefesa() + batalha.getJogador().getPersonagem().getAgilidade());
            batalha.setMonstro(monstro.get());
            batalha.setIniciativa(JOGADOR);
        }
        else{
            Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getPersonagem().getId());
            jogador.get().setSaldo(jogarDados(QUANTIDADE_DADOS, TOTAL_FACES) + batalha.getMonstro().getPersonagem().getDefesa() + batalha.getMonstro().getPersonagem().getAgilidade());
            batalha.setJogador(jogador.get());
            batalha.setIniciativa(MONSTRO);
        }
        return repository.save(batalha);
    }
}
