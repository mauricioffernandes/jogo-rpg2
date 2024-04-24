package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    public static final String MONSTRO = "Monstro";
    @Autowired
    private JogadorRepository repository;
    @Autowired
    private PersonagemService personagemService;
    @Autowired
    private PersonagemRepository personRepository;


    public List<Jogador> findAll(){
        return repository.findAll();
    }

    public Jogador findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Jogador not found with ID: " + id
        ));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Jogador update(Jogador jogador){
        if (jogador.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(jogador);
    }

    public Jogador create(Jogador jogador){
        Optional<Personagem> person = personRepository.findById(jogador.getPersonagem().getId());
        jogador.setCreatedAt(LocalDateTime.now());
            if(person != null){
                jogador.setPersonagem(person.get());
        }
        return repository.save(jogador);
    }

    public Jogador createMonstros(Long id){
        Jogador monster = new Jogador();
        Personagem person = personagemService.generateRandomPersonagem();
        monster.setPersonagem(person);
        monster.setNome(MONSTRO);
        monster.setCreatedAt(LocalDateTime.now());
        return repository.save(monster);
    }
}
