package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final JogadorRepository repository;
    private final PersonagemService personagemService;
    private final PersonagemRepository personagemRepository;
    private static final String MONSTRO = "Monstro";

    public JogadorService(JogadorRepository repository, PersonagemService personagemService, PersonagemRepository personagemRepository) {
        this.repository = repository;
        this.personagemService = personagemService;
        this.personagemRepository = personagemRepository;
    }

    public List<Jogador> findAll() {
        return repository.findAll();
    }

    public Jogador findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador not found with ID: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Jogador update(Jogador jogador) {
        if (jogador.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(jogador);
    }

    public Jogador create(Jogador jogador) {
        Optional<Personagem> personOptional = personagemRepository.findById(jogador.getPersonagem().getId());
        personOptional.ifPresent(jogador::setPersonagem);
        jogador.setCreatedAt(LocalDateTime.now());
        return repository.save(jogador);
    }

    public Optional<Jogador> createMonstros(Long id) {
        Personagem personagem = personagemService.generateRandomPersonagem();
        if (personagem != null) {
            Jogador monster = new Jogador();
            monster.setPersonagem(personagem);
            monster.setNome(MONSTRO);
            monster.setCreatedAt(LocalDateTime.now());
            return Optional.of(repository.save(monster));
        } else {
            return Optional.empty();
        }
    }
}

