package com.apirest.jogorpg.service;

import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PersonagemServiceTest {

    @Mock
    private PersonagemRepository repository;

    private PersonagemService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new PersonagemService(repository);
    }

    @Test
    public void testCreate() {
        Personagem personagem = new Personagem("GIGANTE", 34, 10, 4, 4, 2, 6);
        when(repository.save(personagem)).thenReturn(personagem);

        Personagem createdPersonagem = service.create(personagem);

        assertEquals(personagem, createdPersonagem);
        verify(repository, times(1)).save(personagem);
    }

    @Test
    public void testFindAll() {
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Personagem("GIGANTE", 34, 10, 4, 4, 2, 6));
        when(repository.findAll()).thenReturn(personagens);

        List<Personagem> result = service.findAll();

        assertEquals(personagens, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Personagem personagem = new Personagem("GIGANTE", 34, 10, 4, 4, 2, 6);
        when(repository.findById(id)).thenReturn(Optional.of(personagem));

        Personagem result = service.findById(id);

        assertEquals(personagem, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdate() {
        Personagem personagem = new Personagem("GIGANTE", 34, 10, 4, 4, 2, 6);
        personagem.setId(1L);
        when(repository.save(personagem)).thenReturn(personagem);

        Personagem updatedPersonagem = service.update(personagem);

        assertEquals(personagem, updatedPersonagem);
        verify(repository, times(1)).save(personagem);
    }

    @Test
    public void testCreateRandomPersonagem() {
        Personagem personagem = new Personagem("GIGANTE", 34, 10, 4, 4, 2, 6);
        when(repository.save(any(Personagem.class))).thenReturn(personagem);

        Personagem createdPersonagem = service.createRandomPersonagem();

        assertNotNull(createdPersonagem);
        verify(repository, times(1)).save(any(Personagem.class));
    }

    @Test
    public void testGenerateRandomPersonagem() {
        Personagem randomPersonagem = service.generateRandomPersonagem();

        assertNotNull(randomPersonagem);
        assertNotNull(randomPersonagem.getTipo());
        assertNotNull(randomPersonagem.getQtdVidas());
        assertNotNull(randomPersonagem.getPoder());
        assertNotNull(randomPersonagem.getDefesa());
        assertNotNull(randomPersonagem.getAgilidade());
        assertNotNull(randomPersonagem.getQtdDado());
        assertNotNull(randomPersonagem.getTolalFaces());
    }

}