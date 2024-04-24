package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.service.PersonagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/personagem")
@Api(value = "PERSONAGEM API REST")
@CrossOrigin(origins = "*")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    @GetMapping("")
    @ApiOperation("find a personagens in TODO list")
    public ResponseEntity<List<Personagem>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a personagens by it's id in the TODO list")
    public ResponseEntity<Personagem> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("Create a new personagem in TODO list")
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem){
        return new ResponseEntity<>(service.create(personagem), HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation("Update a personagem on TODO list")
    public ResponseEntity<Personagem> update(@RequestBody Personagem personagem){
        return new ResponseEntity<>(service.update(personagem), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete a personagem on TODO list")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
