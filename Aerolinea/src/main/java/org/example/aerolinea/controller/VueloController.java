package org.example.aerolinea.controller;


import org.example.aerolinea.entity.Vuelo;
import org.example.aerolinea.response.ResponseBase;

import org.example.aerolinea.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vuelo")
public class VueloController {

    @Autowired
    VueloService vueloService;
    @PostMapping("save")
    public ResponseBase saveReserva(@RequestBody Vuelo vuelo){
        return  vueloService.saveVuelo(vuelo);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return vueloService.findById(id);
    }
}
