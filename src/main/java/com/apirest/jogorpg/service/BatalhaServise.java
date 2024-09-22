package com.apirest.jogorpg.service;

import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.common.CalculaDanosJogada;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BatalhaServise extends CalculaDanosJogada {

    private static final int QUANTIDADE_DADOS = 1;
    private static final int TOTAL_FACES = 12;
    private static final String MONSTRO = "Monstro";
    private static final String JOGADOR = "Jogador";
    private final BatalhaRepository repository;
    private final JogadorRepository jogadorRepository;
    private final JogadorService jogadorService;

    public BatalhaServise(BatalhaRepository repository, JogadorRepository jogadorRepository, JogadorService jogadorService) {
        this.repository = repository;
        this.jogadorRepository = jogadorRepository;
        this.jogadorService = jogadorService;
    }


    public List<Batalha> findAll(){
        return repository.findAll();
    }

    public Batalha getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batalha not found with ID: " + id));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Batalha create(Long codJogador) {
        Batalha batalha = new Batalha();
        Jogador jogador = jogadorRepository.findById(codJogador)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador not found with ID: " + codJogador));

        batalha.setCreatedAt(LocalDateTime.now());
        batalha.setJogador(jogador);
        batalha.setMonstro(jogadorService.createMonstros(codJogador).orElseThrow());
        batalha.setIniciativa(jogadaDado() ? JOGADOR : MONSTRO);
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
