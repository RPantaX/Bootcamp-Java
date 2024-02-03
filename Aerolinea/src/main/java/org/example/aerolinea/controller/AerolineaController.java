package org.example.aerolinea.controller;

import org.example.aerolinea.entity.Aerolinea;
import org.example.aerolinea.response.ResponseBase;
import org.example.aerolinea.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/aerolinea")
public class AerolineaController {
    @Autowired
    AerolineaService aerolineaService;
    @PostMapping("save")
    public ResponseBase saveAerolinea(@RequestBody Aerolinea aerolinea){
        return  aerolineaService.saveAerolinea(aerolinea);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return aerolineaService.findById(id);
    }
}
