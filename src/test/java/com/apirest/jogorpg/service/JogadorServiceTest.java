package com.apirest.jogorpg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private PersonagemService personagemService;

    @Mock
    private PersonagemRepository personagemRepository;

    @InjectMocks
    private JogadorService jogadorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll_Success() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador());

        when(jogadorRepository.findAll()).thenReturn(jogadores);

        List<Jogador> result = jogadorService.findAll();
        assertEquals(1, result.size());
        verify(jogadorRepository, times(1)).findAll();
    }

    @Test
    void findById_Success() {
        Jogador jogador = new Jogador();
        jogador.setId(1L);

        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogador));

        Jogador result = jogadorService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(jogadorRepository, times(1)).findById(1L);
    }

    @Test
    void findById_NotFound() {
        when(jogadorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> jogadorService.findById(1L));
        verify(jogadorRepository, times(1)).findById(1L);
    }

    @Test
    void delete_Success() {
        doNothing().when(jogadorRepository).deleteById(1L);

        jogadorService.delete(1L);

        verify(jogadorRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_Success() {
        Jogador jogador = new Jogador();
        jogador.setId(1L);

        when(jogadorRepository.save(jogador)).thenReturn(jogador);

        Jogador result = jogadorService.update(jogador);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(jogadorRepository, times(1)).save(jogador);
    }

    @Test
    void update_InvalidInput() {
        Jogador jogador = new Jogador();  // Sem ID

        assertThrows(InvalidInputException.class, () -> jogadorService.update(jogador));
    }

    @Test
    void create_Success() {
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        Jogador jogador = new Jogador();
        jogador.setPersonagem(personagem);

        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(jogadorRepository.save(any(Jogador.class))).thenReturn(jogador);

        Jogador result = jogadorService.create(jogador);
        assertNotNull(result);
        assertEquals(personagem, result.getPersonagem());
        verify(jogadorRepository, times(1)).save(jogador);
    }

    @Test
    void createMonstros_Success() {
        Personagem personagem = new Personagem();
        Jogador monstro = new Jogador();
        monstro.setPersonagem(personagem);
        monstro.setNome("Monstro");

        when(personagemService.generateRandomPersonagem()).thenReturn(personagem);
        when(jogadorRepository.save(any(Jogador.class))).thenReturn(monstro);

        Optional<Jogador> result = jogadorService.createMonstros(1L);
        assertTrue(result.isPresent());
        assertEquals("Monstro", result.get().getNome());
        verify(jogadorRepository, times(1)).save(any(Jogador.class));
    }

    @Test
    void createMonstros_PersonagemNull() {
        when(personagemService.generateRandomPersonagem()).thenReturn(null);

        Optional<Jogador> result = jogadorService.createMonstros(1L);
        assertFalse(result.isPresent());
        verify(jogadorRepository, never()).save(any(Jogador.class));
    }
}
