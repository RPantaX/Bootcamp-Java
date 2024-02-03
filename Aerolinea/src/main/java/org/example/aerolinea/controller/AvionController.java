package org.example.aerolinea.controller;


import org.example.aerolinea.entity.Avion;
import org.example.aerolinea.response.ResponseBase;

import org.example.aerolinea.service.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/avion")
public class AvionController {

    @Autowired
    AvionService avionService;
    @PostMapping("save")
    public ResponseBase saveAvion(@RequestBody Avion avion){
        return  avionService.saveAvion(avion);
    }
    @GetMapping("get/{id}")
    public ResponseBase findById(@PathVariable Integer id){
        return avionService.findById(id);
    }
}
