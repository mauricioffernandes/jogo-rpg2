package com.apirest.jogorpg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class BatalhaServiceTest {

    @Mock
    private BatalhaRepository batalhaRepository;

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private JogadorService jogadorService;

    @InjectMocks
    private BatalhaServise batalhaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ReturnsListOfBatalhas() {
        List<Batalha> batalhaList = new ArrayList<>();
        batalhaList.add(new Batalha());

        when(batalhaRepository.findAll()).thenReturn(batalhaList);

        List<Batalha> result = batalhaService.findAll();
        assertEquals(1, result.size());
        verify(batalhaRepository, times(1)).findAll();
    }

    @Test
    void getById_ReturnsBatalha_WhenFound() {
        Batalha batalha = new Batalha();
        when(batalhaRepository.findById(anyLong())).thenReturn(Optional.of(batalha));

        Batalha result = batalhaService.getById(1L);
        assertNotNull(result);
        verify(batalhaRepository, times(1)).findById(1L);
    }

    @Test
    void getById_ThrowsException_WhenNotFound() {
        when(batalhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> batalhaService.getById(1L));
        assertEquals("Batalha not found with ID: 1", exception.getMessage());
        verify(batalhaRepository, times(1)).findById(1L);
    }

    @Test
    void create_ReturnsBatalha() {
        Jogador jogador = new Jogador();
        Batalha batalha = new Batalha();

        when(jogadorRepository.findById(anyLong())).thenReturn(Optional.of(jogador));
        when(jogadorService.createMonstros(anyLong())).thenReturn(Optional.of(new Jogador()));
        when(batalhaRepository.save(any(Batalha.class))).thenReturn(batalha);

        Batalha result = batalhaService.create(1L);
        assertNotNull(result);
        verify(batalhaRepository, times(1)).save(any(Batalha.class));
    }

    @Test
    void ataque_Success() {
        Batalha batalha = new Batalha();
        batalha.setIniciativa("Monstro");

        Jogador jogador = new Jogador();
        Personagem personagemJogador = new Personagem();
        personagemJogador.setPoder(10);
        personagemJogador.setAgilidade(5);
        jogador.setPersonagem(personagemJogador);
        batalha.setJogador(jogador);

        Jogador monstro = new Jogador();
        Personagem personagemMonstro = new Personagem();
        personagemMonstro.setPoder(8);
        personagemMonstro.setAgilidade(3);
        monstro.setPersonagem(personagemMonstro);
        batalha.setMonstro(monstro);

        when(jogadorRepository.findByPersonagemMostro(anyLong())).thenReturn(monstro);
        when(jogadorRepository.findById(anyLong())).thenReturn(Optional.of(jogador));

        when(batalhaRepository.save(any(Batalha.class))).thenReturn(batalha);
    }

    @Test
    void defesa_Success() {
        Batalha batalha = new Batalha();
        batalha.setIniciativa("Monstro");

        Jogador jogador = new Jogador();
        Personagem personagemJogador = new Personagem();
        personagemJogador.setPoder(10);
        personagemJogador.setAgilidade(5);
        jogador.setPersonagem(personagemJogador);
        batalha.setJogador(jogador);

        Jogador monstro = new Jogador();
        Personagem personagemMonstro = new Personagem();
        personagemMonstro.setPoder(8);
        personagemMonstro.setAgilidade(3);
        monstro.setPersonagem(personagemMonstro);
        batalha.setMonstro(monstro);

        when(jogadorRepository.findByPersonagemMostro(anyLong())).thenReturn(new Jogador());
        when(batalhaRepository.save(any(Batalha.class))).thenReturn(batalha);
    }
}
