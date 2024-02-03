package org.example.aerolinea.controller;

import org.example.aerolinea.entity.Piloto;
import org.example.aerolinea.response.ResponseBase;

import org.example.aerolinea.service.PilotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/piloto")
public class PilotoController {
    @Autowired
    PilotoService pilotoService;
    @PostMapping("save")
    public ResponseBase savePiloto(@RequestBody Piloto piloto){
        return  pilotoService.savePiloto(piloto);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return pilotoService.findById(id);
    }
}
