package com.apirest.jogorpg.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.service.PersonagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

class PersonagemControllerTest {

    @Mock
    private PersonagemService personagemService;

    @InjectMocks
    private PersonagemController personagemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll_Success() {
        List<Personagem> personagens = Arrays.asList(new Personagem(), new Personagem());
        when(personagemService.findAll()).thenReturn(personagens);

        ResponseEntity<List<Personagem>> response = personagemController.getAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(personagemService, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        when(personagemService.findById(1L)).thenReturn(personagem);

        ResponseEntity<Personagem> response = personagemController.getById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        verify(personagemService, times(1)).findById(1L);
    }

    @Test
    void testCreate_Success() {
        Personagem personagem = new Personagem();
        when(personagemService.create(any(Personagem.class))).thenReturn(personagem);

        ResponseEntity<Personagem> response = personagemController.create(personagem);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personagem, response.getBody());
        verify(personagemService, times(1)).create(any(Personagem.class));
    }

    @Test
    void testUpdate_Success() {
        Personagem personagem = new Personagem();
        when(personagemService.update(any(Personagem.class))).thenReturn(personagem);

        ResponseEntity<Personagem> response = personagemController.update(personagem);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personagem, response.getBody());
        verify(personagemService, times(1)).update(any(Personagem.class));
    }

    @Test
    void testDelete_Success() {
        doNothing().when(personagemService).delete(1L);

        ResponseEntity<HttpStatus> response = personagemController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(personagemService, times(1)).delete(1L);
    }

    @Test
    void testDelete_NotFound() {
        doThrow(new RuntimeException()).when(personagemService).delete(1L);

        ResponseEntity<HttpStatus> response = personagemController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(personagemService, times(1)).delete(1L);
    }
}

