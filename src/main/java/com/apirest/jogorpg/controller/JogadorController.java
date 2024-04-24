package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.service.JogadorService;
import com.apirest.jogorpg.service.PersonagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jogador")
@Api(value = "JOGADOR API REST")
@CrossOrigin(origins = "*")
public class JogadorController {

    @Autowired
    private JogadorService service;

    @Autowired
    private PersonagemService personagemService;

    @GetMapping("")
    @ApiOperation("find a Jogador in TODO list")
    public ResponseEntity<List<Jogador>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("Create a new Jogador in TODO list")
    public ResponseEntity<Jogador> create(@RequestBody Jogador jogador){
        return new ResponseEntity<>(service.create(jogador), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a Jogador by it's id in the TODO list")
    public ResponseEntity<Jogador> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("")
    @ApiOperation("Update a Jogador on TODO list")
    public ResponseEntity<Jogador> update(@RequestBody Jogador jogador){
        return new ResponseEntity<>(service.update(jogador), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete a Jogador on TODO list")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
