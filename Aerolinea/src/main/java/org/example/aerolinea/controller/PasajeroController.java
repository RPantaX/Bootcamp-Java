package org.example.aerolinea.controller;


import org.example.aerolinea.entity.Pasajero;
import org.example.aerolinea.response.ResponseBase;

import org.example.aerolinea.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pasajero")
public class PasajeroController {

    @Autowired
    PasajeroService pasajeroService;
    @PostMapping("save")
    public ResponseBase savePasajero(@RequestBody Pasajero pasajero){
        return  pasajeroService.savePasajero(pasajero);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return pasajeroService.findById(id);
    }
}
