package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.service.BatalhaServise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/batalha")
@Api(value = "BATALHA API REST")
@CrossOrigin(origins = "*")
public class BatalhaController {

    @Autowired
    private BatalhaServise service;

    @GetMapping("/{id}")
    @ApiOperation("Start a battle by your id in the TODO list.")
    public ResponseEntity<Batalha> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation("find a Batalha in TODO list")
    public ResponseEntity<List<Batalha>> historico(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("Create a new Batalha in TODO list")
    public ResponseEntity<Batalha> create(@RequestBody Long cod_jogador){
        return new ResponseEntity<>(service.create(cod_jogador), HttpStatus.CREATED);
    }

    @PostMapping("/ataque")
    @ApiOperation("Ataque a Batalha on TODO list")
    public ResponseEntity<Batalha> ataque(@RequestBody Batalha batalha){
        return new ResponseEntity<>(service.ataque(batalha), HttpStatus.OK);
    }

    @PostMapping("/defesa")
    @ApiOperation("Ataque a Batalha on TODO list")
    public ResponseEntity<Batalha> defesa(@RequestBody Batalha batalha){
        return new ResponseEntity<>(service.defesa(batalha), HttpStatus.OK);
    }

    @PostMapping("/dano")
    @ApiOperation("Ataque a Batalha on TODO list")
    public ResponseEntity<Batalha> calcularDano(@RequestBody Batalha batalha){
        return new ResponseEntity<>(service.calculoDano(batalha), HttpStatus.OK);
    }

    @DeleteMapping("")
    @ApiOperation("Delete a Batalha on TODO list")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
