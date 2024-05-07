package com.apirest.jogorpg.service;

import com.apirest.jogorpg.common.TipoPersonagem;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    private final PersonagemRepository repository;

    public PersonagemService(PersonagemRepository repository) {
        this.repository = repository;
    }

    public Personagem create(Personagem person) {
        return repository.save(person);
    }

    public List<Personagem> findAll() {
        return repository.findAll();
    }

    public Personagem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem not found with ID: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Personagem update(Personagem personagem) {
        if (personagem.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(personagem);
    }

    public Personagem createRandomPersonagem() {
        return repository.save(generateRandomPersonagem());
    }

    public Personagem generateRandomPersonagem() {
        TipoPersonagem tipo = TipoPersonagem.getRandom();
        switch (tipo) {
            case ORC:
                return new Personagem(tipo.name(), 42, 7, 1, 2, 3, 4);
            case GIGANTE:
                return new Personagem(tipo.name(), 34, 10, 4, 4, 2, 6);
            case LOBISOMEN:
                return new Personagem(tipo.name(), 34, 7, 4, 7, 2, 4);
        }
        return null;
    }
}
